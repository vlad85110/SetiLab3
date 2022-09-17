package controller.commands;

import exceptions.command.CommandException;
import model.Condition;
import model.Data;

import java.net.http.HttpClient;

public class SearchPlaces implements Command {
    private final String latitude;
    private final String longitude;
    private final HttpClient client;

    public SearchPlaces(String coordinates, HttpClient client) throws CommandException {
        try {
            var splitCoords = coordinates.split(" ");
            this.latitude = splitCoords[0];
            this.longitude = splitCoords[1];
            this.client = client;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CommandException("wrong input");
        }
    }

    @Override
    public Condition run(Data data) throws CommandException {
        return Condition.PlacesShowing;
    }
}
