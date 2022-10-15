package controller.commands;

import controller.commands.arguments.CommandArgs;
import exceptions.command.CommandException;
import model.Condition;
import model.Data;
import view.View;

public class Exit implements Command {
    public Exit(CommandArgs args) {

    }

    @Override
    public Condition run(Data data, View view) throws CommandException {
        return Condition.EXIT;
    }
}
