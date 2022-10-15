package exceptions;

public class AppException extends Exception {
    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(Throwable cause) {
        super(cause);
    }
}
