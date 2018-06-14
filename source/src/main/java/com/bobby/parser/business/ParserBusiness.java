package com.bobby.parser.business;

import com.bobby.parser.Duration;
import com.bobby.parser.LineData;
import com.bobby.parser.config.Constants;
import com.bobby.parser.exceptions.EmptyLineException;
import com.bobby.parser.exceptions.LineParseException;
import com.bobby.parser.model.Blocked;
import com.bobby.parser.model.Batch;
import com.bobby.parser.model.Request;
import com.bobby.parser.repository.BatchRepository;
import com.bobby.parser.repository.BlockedRepo;
import com.bobby.parser.repository.ReqRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import static com.bobby.parser.Utils.getEndDate;
import static com.bobby.parser.Utils.isEqualOrAfter;
import static com.bobby.parser.Utils.isEqualOrBefore;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/08
 */
@Service
public class ParserBusiness {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired private LineDeserializer deserializer;
    @Autowired private BatchRepository batchRepository;
    @Autowired private ReqRepo requestRepository;
    @Autowired private BlockedRepo blockedRepository;

    @Value("${"+ Constants.DB_ENABLED_KEY +":"+ Constants.DB_ENABLED +"}")
    private boolean db;
    @Value("${"+Constants.DB_ASYNC_KEY +":"+ Constants.DB_ASYNC +"}")
    private boolean async;
    @Value("${"+ Constants.DB_BATCH_COUNT_KEY +":"+ Constants.DB_BATCH_COUNT +"}")
    private Integer batchCount;

    private Map<String,Integer> requests = new HashMap<>();
    private List<Request> pendingSaveRequests = new ArrayList<>();
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int threshold;
    private Batch batch;

    @PostConstruct
    public void init(){
        if (batchCount == null || batchCount < 1){
            batchCount = 1; // to prevent divide by zero exception
        }
    }

    public void parse(String filename, LocalDateTime startDate, Duration duration,
        int threshold) throws IOException, LineParseException {

        this.startDate = startDate;
        this.endDate = getEndDate(startDate, duration);
        this.threshold = threshold;
        //
        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader(filename));
        }catch (FileNotFoundException e){
            log.error("Failed to load file: {}",filename);
            throw e;
        }
        //
        saveRequests(br.lines());
        //
        analyzeRequests();
    }

    private void saveRequests(Stream<String> lines){
        // create and insert parse batch
        createAndSaveBatch();
        log.info("---------- Parsing the file started ----------");
        log.info("Batch ID in DB: {}",batch.getId());
        //
        final int[] lineNumber = {0};
        lines.forEach(line -> {
            try {
                lineNumber[0]++;
                System.out.print("\r----- Parsing Line "+lineNumber[0]+" ... ");
                saveSingleLineData(deserializer.read(line), lineNumber[0]);
            } catch (LineParseException e) {
                log.error("Failed to parse line #{} : {}", lineNumber, e.getMessage());
            } catch (EmptyLineException e) {
                log.warn("Line #{} is empty", lineNumber);
            }
        });
        //
        saveRemainingRequests();
    }

    private void saveSingleLineData(LineData data, int lineNumber) {
        if (data == null){
            return;
        }
        // 1. persist the data to DB
        saveRequest(data);
        // 2. check for threshold
        // keeping the requests data outside time range is useless, so:
        // if request date is in time range (startDate < date < endDate)
        if (isEqualOrAfter(data.getDate(), startDate)
            && isEqualOrBefore(data.getDate(), endDate)) {
            // add it to request list or increase its occurrence value if already exists
            Integer occurrence = requests.get(data.getAddress());
            if (occurrence == null) {
                occurrence = 1;
                requests.put(data.getAddress(), occurrence);
            } else {
                requests.put(data.getAddress(), ++occurrence);
            }
        }
    }


    private void analyzeRequests(){
        // query records that exceed threshold
        // log to console
        // persist into DB
        log.info("------------------- Statistics ---------------------");
        log.info("Blocked IPs threshold {} exceed between {} and {} :",
            threshold, startDate, endDate);
        long[] totalBlocked = {0};
        requests.entrySet().stream().filter(request -> request.getValue() > threshold)
            .forEach(request -> {
                // log to console
                log.info("{}\t\t\tRequest Count: {}",request.getKey(), request.getValue());
                // persist into DB
                saveBlocked(request.getKey(), request.getValue());
                totalBlocked[0]++;
            });
        log.info("---------------- Total Blocked : {} ----------------", totalBlocked);
    }


    // ------------ repo works ------------------------------------------------

    private void createAndSaveBatch(){
        if (!db){
            return;
        }
        batch = new Batch()
            .setCreationDate(LocalDateTime.now())
            .setStartDate(startDate).setEndDate(endDate)
            .setThreshold(threshold);
        batch = batchRepository.save(batch);
    }

    /**
     * Adds and keeps data in a list and saves them into DB once the list size reaches batchCount (100 by default)
     * @param data
     */
    private void saveRequest(LineData data) {
        if (!db){
            return;
        }
        Request request = new Request().setBatch(batch)
            .setAddress(data.getAddress())
            .setDate(data.getDate()).setData(data.getData());
        pendingSaveRequests.add(request);
        if (pendingSaveRequests.size() == batchCount){
//        if (pendingSaveRequests.size() % batchCount == 0){
            List<Request> tempRequests = new ArrayList<>(pendingSaveRequests);
            pendingSaveRequests.clear();
            requestRepository.persistAll(tempRequests);
        }
    }

    private void saveRemainingRequests(){
        requestRepository.persistAll(pendingSaveRequests);
    }

    private void saveBlocked(String address, Integer count) {
        if (!db){
            return;
        }
        String comment = "Address blocked due to exceed of threshold "+threshold
            +" between "+startDate+" and "+endDate;
        Blocked blocked = new Blocked().setBatch(batch)
            .setAddress(address).setCount(count).setComment(comment);
        blockedRepository.persist(blocked);
    }

}
