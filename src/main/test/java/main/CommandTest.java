package main;


import controller.commands.requestcommands.GetPlaceDescription;
import controller.commands.requestcommands.GetWeather;
import controller.commands.requestcommands.SearchLocations;
import controller.commands.requestcommands.SearchPlaces;
import exceptions.command.CommandException;
import model.Data;
import model.Point;
import org.junit.Assert;
import org.junit.Test;
import parser.ConfigParser;

import java.io.IOException;
import java.net.http.HttpClient;

public class CommandTest {
//    private final ConfigParser configParser = new ConfigParser();
//    private final HttpClient client = HttpClient.newHttpClient();

    public CommandTest() throws IOException {}

    @Test
    public void searchLocationTest() throws CommandException {
//        Data data = new Data();
//        SearchLocations searchLocation = new SearchLocations(configParser, "цветной проезд", client);
//        try {
//            searchLocation.run(data, null);
//        } catch (CommandException e) {
//            //
//        }
//        if (data.getLocations().isEmpty()) {
//            Assert.fail();
//        }
    }

    @Test
    public void getWeatherTest() throws CommandException {
//        Data data = new Data();
//        GetWeather getWeather = new GetWeather(configParser, client, new Point(44.9713756, 37.2925047));
//        try {
//            getWeather.run(data, null);
//        } catch (NullPointerException e) {
//            //
//        }
    }

    @Test
    public void searchPlacesTest() throws CommandException {
//        Data data = new Data();
//        SearchPlaces searchPlaces = new SearchPlaces(configParser, client, new Point(44.9713756, 37.2925047));
//        try {
//            searchPlaces.run(data, null);
//        } catch (NullPointerException e) {
//            //
//        }
    }

    @Test
    public void getPlaceDescriptionTest() throws CommandException{
//        Data data = new Data();
//        GetPlaceDescription getPlaceDescription = new GetPlaceDescription(configParser, client, "W286786280");
//        try {
//            getPlaceDescription.run(data, null);
//        } catch (NullPointerException e) {
//            //
//        }
    }
}
