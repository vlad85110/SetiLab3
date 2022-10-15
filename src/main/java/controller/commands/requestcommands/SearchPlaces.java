package controller.commands.requestcommands;

import com.fasterxml.jackson.core.JsonProcessingException;
import controller.commands.config.CommandConfig;
import controller.commands.config.SearchPlacesConfig;
import exceptions.command.CommandException;
import exceptions.view.ViewException;
import model.Data;
import model.Place;
import model.Point;
import org.json.JSONArray;
import parser.ConfigParser;
import view.View;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SearchPlaces extends FindByCoordinatesCommand {
    public SearchPlaces(ConfigParser configParser, HttpClient client, Point point) {
        super(configParser, client);
        this.point = point;
    }

    @Override
    protected Class<? extends CommandConfig> getConfigClass() {
        return SearchPlacesConfig.class;
    }

    @Override
    protected void handleResponse(HttpResponse<String> response, Data data, View view, CommandConfig config)
            throws CommandException {

        JSONArray placesResponseList = new JSONArray(response.body());
        List<Place> places = new ArrayList<>();

        for (var i : placesResponseList) {
            try {
                var place = mapper.readValue(i.toString(), Place.class);
                if (!"".equals(place.getName())) {
                    var xid = place.getXid();
                    places.add(place);

                    GetPlaceDescription getPlaceDescription = new GetPlaceDescription(configParser, client, xid);
                    Runnable getPlaceDescriptionRunnable = () -> {
                        try {
                            getPlaceDescription.run(data, view);
                        } catch (CommandException ignored) {}
                    };
                    CompletableFuture.runAsync(getPlaceDescriptionRunnable);
                }
            } catch (JsonProcessingException e) {
                throw new CommandException(e);
            }
        }

        data.addPlaces(point, places);
        try {
            view.updatePlaces(places);
        } catch (ViewException e) {
            throw new CommandException(e);
        }
    }
}
