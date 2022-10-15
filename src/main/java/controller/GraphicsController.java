package controller;

import controller.commands.Command;
import controller.commands.arguments.CommandArgs;
import factory.CommandFactory;
import exceptions.controller.ControllerException;
import parser.ConfigParser;
import view.GraphicsView;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.http.HttpClient;

public class GraphicsController implements Controller {
    private final ConfigParser parser;
    private final CommandFactory factory;
    private final HttpClient client = HttpClient.newHttpClient();
    private final GraphicsView view;

    public GraphicsController(GraphicsView view) throws IOException {
        this.view = view;
        parser = new ConfigParser();
        factory = new CommandFactory(parser);
    }

    @Override
    public Command getCommand() throws ControllerException {
        var action = view.waitAction().split(" ", 2);
        String name = action[0];

        String params;
        try {
            params = action[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            params = "";
        }

        try {
            return factory.createObject(name, new CommandArgs(parser, client, params));
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                 IllegalAccessException | NoSuchMethodException e) {
            throw new ControllerException("no such command");
        }
    }
}
