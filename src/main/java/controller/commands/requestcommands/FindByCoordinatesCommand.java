package controller.commands.requestcommands;

import controller.commands.requestcommands.AbstractRequestCommand;
import controller.commands.config.CommandConfig;
import controller.commands.config.FindByCoordinatesConfig;
import exceptions.command.CommandException;
import model.Point;
import org.apache.http.client.utils.URIBuilder;
import parser.ConfigParser;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;

public abstract class FindByCoordinatesCommand extends AbstractRequestCommand {
    protected Point point;

    public FindByCoordinatesCommand(ConfigParser configParser, HttpClient client) {
        super(configParser, client);
    }

    @Override
    protected URI createURI(CommandConfig config) throws CommandException {
        var builder =  new URIBuilder(super.createURI(config));

        FindByCoordinatesConfig coordinatesConfig = (FindByCoordinatesConfig)config;
        builder.addParameter(coordinatesConfig.getLatKeyName(), String.valueOf(point.getLat()));
        builder.addParameter(coordinatesConfig.getLngKeyName(), String.valueOf(point.getLng()));

        URI uri;
        try {
            uri = builder.build();
        } catch (URISyntaxException e) {
            throw new CommandException(e);
        }

        return uri;
    }
}
