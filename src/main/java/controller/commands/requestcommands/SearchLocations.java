package controller.commands.requestcommands;

import com.fasterxml.jackson.core.JsonProcessingException;
import controller.commands.arguments.CommandArgs;
import controller.commands.config.CommandConfig;
import controller.commands.config.SearchLocationsConfig;
import controller.commands.requestcommands.AbstractRequestCommand;
import exceptions.command.CommandException;
import exceptions.view.ViewException;
import model.Condition;
import model.Data;
import model.Location;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import parser.ConfigParser;
import view.View;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.*;

public class SearchLocations extends AbstractRequestCommand {
    private final String locationName;

    public SearchLocations(CommandArgs args) {
        super(args.parser(), args.client());
        this.locationName = args.params();
    }

    public SearchLocations(ConfigParser configParser, String locationName, HttpClient client) {
        super(configParser, client);
        this.locationName = locationName;
    }

    @Override
    protected Class<? extends CommandConfig> getConfigClass() {
        return SearchLocationsConfig.class;
    }

    @Override
    protected void handleResponse(HttpResponse<String> response, Data data, View view,
                                  CommandConfig config) throws  CommandException {

        SearchLocationsConfig searchLocationsConfig = (SearchLocationsConfig)config;

        var locations = createLocationsFromResponse(response,
                searchLocationsConfig.getResultsKeyName());
        data.setLocations(locations);

        try {
            view.updateLocations(locations);
        } catch (ViewException | NullPointerException e) {
            throw new CommandException("cannot update view");
        }
    }

    @Override
    protected URI createURI(CommandConfig config) throws CommandException {
        var builder = new URIBuilder(super.createURI(config));

        URI uri;
        SearchLocationsConfig locationsConfig = (SearchLocationsConfig)config;
        builder.addParameter(locationsConfig.getLocationNameParam(), locationName);

        try {
            uri = builder.build();
        } catch (URISyntaxException e) {
            throw new CommandException(e);
        }

        return uri;
    }

    private List<Location> createLocationsFromResponse(HttpResponse<String> response, String resultsKeyName) {
        var res = new ArrayList<Location>();

        JSONObject jsonObject = new JSONObject(response.body());
        var responseLocations = jsonObject.getJSONArray(resultsKeyName);
        for (var responseLocation : responseLocations) {
            Location location  = null;
            try {
                location = mapper.readValue(responseLocation.toString(), Location.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            res.add(location);
        }
        return res;
    }
}
