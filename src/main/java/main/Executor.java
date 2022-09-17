package main;

import controller.Controller;
import controller.GraphicsController;
import controller.commands.Command;
import exceptions.command.CommandException;
import exceptions.controller.ControllerException;
import exceptions.view.ViewException;
import model.Condition;
import model.Data;
import view.GraphicsView;
import view.View;

import java.util.List;

public class Executor {
    private Condition condition;
    private final Controller controller;
    private final Data data;
    private final View view;

    public Executor() {
        this.view = new GraphicsView();
        this.controller = new GraphicsController((GraphicsView)view);
        this.data = new Data();
    }

    public void startApp() {
        Command command;

        while (condition != Condition.Exit) {
            try {
                command = controller.getCommand();
            } catch (ControllerException e) {
                throw new RuntimeException(e);
            }

            try {
                condition = command.run(data);
            } catch (CommandException e) {
                throw new RuntimeException(e);
            }

            List<?> dataToUpdate = null;
            switch (condition) {
                case LocationChoosing -> dataToUpdate = data.getLocations();
                case PlacesShowing -> dataToUpdate = data.getPlaces();

                case Exit -> {
                    stopApp();
                    return;
                }
            }

            try {
                view.updateView(condition, dataToUpdate);
            } catch (ViewException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stopApp() {
        //todo
    }
}
