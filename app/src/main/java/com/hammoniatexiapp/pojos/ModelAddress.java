package com.hammoniatexiapp.pojos;

import java.io.Serializable;

public class ModelAddress implements Serializable {

    String address;
    String lat;
    String lon;

    public ModelAddress(String address, String lat, String lon) {
        this.address = address;
        this.lat = lat;
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

}
