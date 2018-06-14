package com.bobby.parser.business;

import com.bobby.parser.LineData;
import com.bobby.parser.config.Constants;
import com.bobby.parser.exceptions.EmptyLineException;
import com.bobby.parser.exceptions.LineParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/08
 */
@Component
public class DefaultLineDeserializer implements LineDeserializer {

    private final Log log = LogFactory.getLog(getClass());

    @Value("${line.delimiter:"+ Constants.LINE_DELIMITER+"}")
    private String delimiter;
    @Value("${line.parts.date:"+ Constants.LINE_INDEX_DATE+"}")
    private int dateIndex;
    @Value("${line.parts.address:"+ Constants.LINE_INDEX_ADDRESS+"}")
    private int addressIndex;
    @Value("${line.parts.data:"+ Constants.LINE_INDEX_DATA+"}")
    private int dataIndex;

    @Autowired private DateTimeFormatter dateTimeFormatter;

    @Override public LineData read(String line)
        throws LineParseException, EmptyLineException {
        if (!StringUtils.hasText(line)){
//            log.warn("Encountered empty line");
            throw new EmptyLineException();
        }
        String[] parts = line.split(delimiter, dataIndex + 1);
        LineData data = new LineData();
        try {
            data.setDate(getDate(parts));
            data.setAddress(getAddress(parts));
            data.setData(getData(parts));
        }catch (Exception e){
            throw new LineParseException(e);
        }
        return data;
    }

    private LocalDateTime getDate(String[] parts){
        String date = parts[dateIndex];
        if (!StringUtils.hasText(date)){
            return null;
        }
        return LocalDateTime.parse(date.trim(), dateTimeFormatter);
    }

    private String getAddress(String[] parts){
        String address = parts[addressIndex];
        if (!StringUtils.hasText(address)){
            return null;
        }
        return address.trim();
    }

    private String getData(String[] parts){
        return parts[dataIndex];
    }

    // --------------------- getters and setters

    public String getDelimiter() {
        return delimiter;
    }

    public DefaultLineDeserializer setDelimiter(String delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    public int getDateIndex() {
        return dateIndex;
    }

    public DefaultLineDeserializer setDateIndex(int dateIndex) {
        this.dateIndex = dateIndex;
        return this;
    }
}
