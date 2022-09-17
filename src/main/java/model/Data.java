package model;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Location> locations = new ArrayList<>();
    private List<Place> places = new ArrayList<>();

    public List<Location> getLocations() {
        return locations;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
