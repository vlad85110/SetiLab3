package exceptions.command;

import exceptions.AppException;

public class CommandException extends AppException {
    public CommandException() {
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}
