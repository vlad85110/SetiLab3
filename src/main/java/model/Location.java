package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import reflection.Ignore;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private String name;
    private String country;
    private String state;
    private String city;
    @Ignore
    private Point point;
    @Ignore
    private Weather weather;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public int hashCode() {
        return point.toString().hashCode();
    }
}
