package view;

import exceptions.view.ViewException;
import model.Location;
import model.Place;
import model.Weather;

import java.util.List;

public interface View {
    void updateLocations(List<Location> locations) throws ViewException;
    void updateWeather(Weather weather) throws ViewException;
    void updatePlaces(List<Place> places) throws ViewException;
    void updatePlaceDescription(String description) throws ViewException;
    void clearPlaces();

    void clearWeather();
    void setActiveDescription(String xid);
}
