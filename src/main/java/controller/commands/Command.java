package controller.commands;

import exceptions.command.CommandException;
import model.Condition;
import model.Data;
import view.View;

public interface Command {
    Condition run(Data data, View view) throws CommandException;
}
