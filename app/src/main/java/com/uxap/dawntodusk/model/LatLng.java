package com.uxap.dawntodusk.model;

import java.io.Serializable;

public class LatLng implements Serializable {
    private double lattitude;
    private double longitude;

    public LatLng(double lattitude, double longitude) {
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LatLng{" +
                "lattitude=" + lattitude +
                ", longitude=" + longitude +
                '}';
    }
}
