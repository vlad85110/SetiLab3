package exceptions.command;

public class IncorrectKeyException extends CommandException {
    public IncorrectKeyException() {
    }

    public IncorrectKeyException(String message) {
        super(message);
    }

    public IncorrectKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectKeyException(Throwable cause) {
        super(cause);
    }

    public IncorrectKeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
