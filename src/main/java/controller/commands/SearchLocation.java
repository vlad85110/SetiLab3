package controller.commands;

import exceptions.command.CommandException;
import exceptions.command.IncorrectKeyException;
import model.Condition;
import model.Data;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

public class SearchLocation implements Command {
    private final String locationName;
    private final HttpClient client;

    public SearchLocation(String location, HttpClient client) {
        this.locationName = location;
        this.client = client;
    }

    @Override
    public Condition run(Data data) throws CommandException {
        URIBuilder builder = null;
        try {
            builder = new URIBuilder("https://graphhopper.com/api/1/geocode");
        } catch (URISyntaxException e) {
            throw new CommandException(e);
        }

        builder.addParameter("locale", "en");
        builder.addParameter("q", locationName);
        builder.addParameter("key", "a432ff40-a57c-4044-a0f7-8ff63180cf1e");

        URI uri = null;
        try {
            uri = builder.build();
        } catch (URISyntaxException e) {
            throw new CommandException(e);
        }
        HttpRequest request = HttpRequest.
                newBuilder().uri(uri).build();

        HttpResponse<String> response = null;
        int code;
        try {
            response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new CommandException(e);
        }

        code = response.statusCode();

        if (code >= 400) {
            throw new IncorrectKeyException();
        }
        var obj = new JSONObject(response.body());

        return Condition.LocationChoosing;
    }
}
