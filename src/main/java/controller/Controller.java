package controller;

import controller.commands.Command;
import exceptions.controller.ControllerException;

public interface Controller {
    Command getCommand() throws ControllerException;
}
