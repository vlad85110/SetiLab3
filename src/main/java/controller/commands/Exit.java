package controller.commands;

import exceptions.command.CommandException;
import model.Condition;
import model.Data;

public class Exit implements Command {
    @Override
    public Condition run(Data data) throws CommandException {
        return Condition.Exit;
    }
}
