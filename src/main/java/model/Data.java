package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {
    private List<Location> locations;
    private HashMap<Point, Location> locationHashMap;

    private final Map<Location, List<Place>> locationPlacesMap = new HashMap<>();
    private final Map<String, Place> placesIdMap = new HashMap<>();

    private Location chosenLocation;

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;

        locationHashMap = new HashMap<>();
        for (var location: locations) {
            locationHashMap.put(location.getPoint(), location);
        }
    }

    public Location getChosenLocation() {
        return chosenLocation;
    }

    public void setChosenLocation(Location chosenLocation) {
        this.chosenLocation = chosenLocation;
    }

    public boolean isLocationContainsPlaces(Location location) {
        return locationPlacesMap.containsKey(location);
    }

    public void setLocationPlaces(List<Place> places, Location location) {
        locationPlacesMap.put(location, places);
    }

    public Location getLocation(int index) {
        return locations.get(index);
    }

    public void addPlaces(Point point, List<Place> places) {
        var location = locationHashMap.get(point);
        locationPlacesMap.put(location, places);

        for (var place: places) {
            placesIdMap.put(place.getXid(), place);
        }
    }

    public List<Place> getPlaces(Location location) {
        return locationPlacesMap.get(location);
    }

    public void addWeather(Point point, Weather weather) {
        locationHashMap.get(point).setWeather(weather);
    }

    public void addDescription(String xid, String description) {
        placesIdMap.get(xid).setDescription(description);
    }

    public String getPlaceDescription(String xid) {
        return placesIdMap.get(xid).getDescription();
    }
}
