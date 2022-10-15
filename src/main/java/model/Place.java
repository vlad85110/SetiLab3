package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import reflection.Ignore;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {
    private String name;
    @Ignore
    private double dist;
    @Ignore
    private String xid;
    @Ignore
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return xid.hashCode();
    }
}
