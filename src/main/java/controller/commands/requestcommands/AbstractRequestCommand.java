package controller.commands.requestcommands;

import controller.commands.AbstractCommand;
import controller.commands.config.CommandConfig;
import exceptions.command.CommandException;
import exceptions.command.IncorrectKeyException;
import model.Condition;
import model.Data;
import org.apache.http.client.utils.URIBuilder;
import parser.ConfigParser;
import view.View;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class AbstractRequestCommand extends AbstractCommand {
    protected final HttpClient client;

    public AbstractRequestCommand(ConfigParser configParser, HttpClient client) {
        super(configParser);
        this.client = client;
    }

    @Override
    public final Condition run(Data data, View view) throws CommandException {
        var configOpt = getConfig();
        if (configOpt.isEmpty()) {
            throw new CommandException("no config for command");
        }

        var config = configOpt.get();
        var uri = createURI(config);

        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
        HttpResponse<String> response;
        int code;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            throw new CommandException(e);
        }

        code = response.statusCode();
        if (code >= 400) {
            throw new IncorrectKeyException();
        }

        handleResponse(response, data, view, config);
        return Condition.CONTINUE;
    }

    protected URI createURI(CommandConfig config) throws CommandException {
        URIBuilder builder;

        try {
            builder = new URIBuilder(config.getBaseURL());
        } catch (URISyntaxException e) {
            throw new CommandException(e);
        }

        for (var item : config.getParams().entrySet()) {
            builder.addParameter(item.getKey(), item.getValue());
        }

        try {
            return builder.build();
        } catch (URISyntaxException e) {
            throw new CommandException(e);
        }
    }

    protected abstract void handleResponse(HttpResponse<String> response, Data data, View view,
                                           CommandConfig config) throws CommandException;
}
