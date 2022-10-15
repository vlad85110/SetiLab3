package model;

public class Point {
    private double lat;
    private double lng;

    public Point(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Point() {
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Point{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
