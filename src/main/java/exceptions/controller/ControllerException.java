package exceptions.controller;

import exceptions.AppException;

public class ControllerException extends AppException {
    public ControllerException(String message) {
        super(message);
    }
}
