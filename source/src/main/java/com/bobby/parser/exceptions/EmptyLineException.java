package com.bobby.parser.exceptions;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/09
 */
public class EmptyLineException extends Exception {
    public EmptyLineException() {
        super();
    }

    public EmptyLineException(String message) {
        super(message);
    }

    public EmptyLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyLineException(Throwable cause) {
        super(cause);
    }

    protected EmptyLineException(String message, Throwable cause,
        boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
