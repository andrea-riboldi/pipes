package it.micronixnetwork.pipe;

public class UnitException extends PipeException {

    private static final long serialVersionUID = 1L;

    public UnitException() {
    }

    public UnitException(String message) {
	super(message);
    }

    public UnitException(Throwable cause) {
	super(cause);
    }

    public UnitException(String message, Throwable cause) {
	super(message, cause);
    }

    public UnitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}
