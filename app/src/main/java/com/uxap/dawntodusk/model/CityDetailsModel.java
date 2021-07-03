package com.uxap.dawntodusk.model;

public class CityDetailsModel {
    private String cityName;
    private LatLng cityLatLng;

    public CityDetailsModel(String cityName, LatLng cityLatLong) {
        this.cityName = cityName;
        this.cityLatLng = cityLatLong;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public LatLng getCityLatLong() {
        return cityLatLng;
    }

    public void setCityLatLong(LatLng cityLatLong) {
        this.cityLatLng = cityLatLong;
    }
}
