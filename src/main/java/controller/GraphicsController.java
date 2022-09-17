package controller;

import controller.commands.Command;
import controller.factory.CommandFactory;
import exceptions.controller.ControllerException;
import view.GraphicsView;

import java.net.http.HttpClient;

public class GraphicsController implements Controller {
    private final CommandFactory factory = new CommandFactory();
    private final HttpClient client = HttpClient.newHttpClient();
    private final GraphicsView view;

    public GraphicsController(GraphicsView view) {
        this.view = view;
    }

    @Override
    public Command getCommand() throws ControllerException {
        return null;
    }
}
