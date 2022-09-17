package controller.commands;

import exceptions.command.CommandException;
import model.Condition;
import model.Data;

public interface Command {
    Condition run(Data data) throws CommandException;
}
