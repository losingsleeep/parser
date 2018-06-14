package com.bobby.parser;

import com.bobby.parser.business.ParserBusiness;
import com.bobby.parser.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class ParserApplication implements CommandLineRunner {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Value("${"+Constants.DATE_FORMAT_IN_ARGS_KEY+":"+ Constants.DATE_FORMAT_IN_ARGS +"}")
	private String startDateFormat;

	@Value("${"+Constants.START_DATE_ARG_NAME+":}")
	private String startDateArg;

	@Value("${"+Constants.DURATION_ARG_NAME+":}")
	private String durationArg;

	@Value("${"+Constants.THRESHOLD_ARG_NAME+":0}")
	private int thresholdArg;

	@Value("${"+ Constants.FILE_ARG_NAME +":access.log}")
	private String fileArg;

	@Value("${"+ Constants.DB_ENABLED_KEY +":"+ Constants.DB_ENABLED +"}")
	private boolean db;

	@Value("${"+ Constants.DB_ASYNC_KEY +":"+ Constants.DB_ASYNC +"}")
	private boolean async;

	@Value("${"+ Constants.DB_BATCH_COUNT_KEY +":"+ Constants.DB_BATCH_COUNT +"}")
	private Integer batchCount;

	@Autowired private ParserBusiness parserBusiness;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ParserApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.setLogStartupInfo(false);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

	@Override public void run(String... args) throws Exception {
		log.info("");
		log.info("---------- CONFIG & INPUT -----------");
		log.info("startDateFormat: {}",startDateFormat);
		log.info("startDate: {}",startDateArg);
		log.info("duration: {}",durationArg);
		log.info("threshold: {}",thresholdArg);
		log.info("file: {}",fileArg);
		log.info("db.enabled: {}",db);
		log.info("db.async: {}",async);
		log.info("db.batch-count: {}",batchCount);
		log.info("-------------------------------------");
		parserBusiness.parse(fileArg, getStartDate(startDateArg),
			getDuration(durationArg), thresholdArg);
		log.info("Waiting for queued DB jobs to finish. This might take minutes...");
	}

	private LocalDateTime getStartDate(String startDateArg){
		if (StringUtils.isEmpty(startDateArg)){
			throw new IllegalArgumentException("Invalid '"+Constants.START_DATE_ARG_NAME+"' value");
		}
		try {
			return LocalDateTime.parse(startDateArg, DateTimeFormatter.ofPattern(startDateFormat));
		}catch (Exception e){
			throw new IllegalArgumentException("Invalid '"+Constants.START_DATE_ARG_NAME+"' value",e);
		}
	}

	private Duration getDuration(String durationArg){
		if (StringUtils.isEmpty(durationArg)){
			throw new IllegalArgumentException("Invalid '"+Constants.DURATION_ARG_NAME+"' value");
		}
		try{
			return Duration.valueOf(durationArg.toUpperCase());
		}catch (IllegalArgumentException e){
			throw new IllegalArgumentException("Invalid '"+Constants.DURATION_ARG_NAME+"' value",e);
		}
	}

}
