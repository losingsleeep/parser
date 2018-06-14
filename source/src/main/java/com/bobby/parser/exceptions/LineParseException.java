package com.bobby.parser.exceptions;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/08
 */
public class LineParseException extends Exception {

    public LineParseException() {
        super();
    }

    public LineParseException(String message) {
        super(message);
    }

    public LineParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LineParseException(Throwable cause) {
        super(cause);
    }

    protected LineParseException(String message, Throwable cause,
        boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
