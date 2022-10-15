package main;

import controller.Controller;
import controller.GraphicsController;
import controller.commands.Command;
import controller.commands.Exit;
import exceptions.command.CommandException;
import exceptions.controller.ControllerException;
import exceptions.view.ViewException;
import model.Condition;
import model.Data;
import view.GraphicsView;
import view.View;

import java.io.IOException;
import java.util.List;

public class Executor {
    private volatile Condition condition;
    private final Controller controller;
    private final Data data;
    private final View view;

    public Executor() throws IOException {
        this.view = new GraphicsView();
        this.controller = new GraphicsController((GraphicsView)view);
        this.data = new Data();
    }

    public void startApp() throws ViewException, ControllerException {
        while (condition != Condition.EXIT) {
            Command command = null;
            while (command == null) {
                try {
                    command = controller.getCommand();
                } catch (ControllerException ignored) {}
            }

            try {
                condition = command.run(data, view);
            } catch (CommandException ignored) {}
        }
    }
}
