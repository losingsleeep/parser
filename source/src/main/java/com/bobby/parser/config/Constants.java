package com.bobby.parser.config;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/08
 */
public class Constants {

    public static final String START_DATE_ARG_NAME = "startDate";
    public static final String DURATION_ARG_NAME = "duration";
    public static final String THRESHOLD_ARG_NAME = "threshold";
    public static final String FILE_ARG_NAME = "accesslog";
    //
    public static final String DATE_FORMAT_IN_FILE = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_FORMAT_IN_ARGS = "yyyy-MM-dd.HH:mm:ss";
    public static final String LINE_DELIMITER = "|";
    public static final String LINE_INDEX_DATE = "0";
    public static final String LINE_INDEX_ADDRESS = "1";
    public static final String LINE_INDEX_DATA = "2";
    //
    public static final boolean DB_ENABLED = true;
    public static final boolean DB_ASYNC = true;
    public static final String DB_BATCH_COUNT = "100";

    //
    // config key names
    //

    public static final String DATE_FORMAT_IN_FILE_KEY = "date.format.file";
    public static final String DATE_FORMAT_IN_ARGS_KEY = "date.format.args";

    public static final String DB_ENABLED_KEY = "db.enabled";
    public static final String DB_ASYNC_KEY = "db.async";
    public static final String DB_BATCH_COUNT_KEY = "db.batch-count";


}
