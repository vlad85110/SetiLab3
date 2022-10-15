package controller.commands;

import controller.commands.arguments.CommandArgs;
import exceptions.command.CommandException;
import exceptions.view.ViewException;
import model.Condition;
import model.Data;
import view.View;

public class ShowDescription implements Command {
    private final String xid;

    public ShowDescription(CommandArgs args) {
        this.xid = args.params();
    }

    @Override
    public Condition run(Data data, View view) throws CommandException {
        var description = data.getPlaceDescription(xid);
        try {
            view.updatePlaceDescription(description);
        } catch (ViewException e) {
            throw new CommandException(e);
        }
        return Condition.CONTINUE;
    }
}
