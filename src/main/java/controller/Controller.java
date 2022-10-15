package controller;

import controller.commands.Command;
import exceptions.controller.ControllerException;
import view.View;

public interface Controller {
    Command getCommand() throws ControllerException;
}
