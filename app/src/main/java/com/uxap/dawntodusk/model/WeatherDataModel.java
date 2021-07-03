package com.uxap.dawntodusk.model;

public class WeatherDataModel {
    private String dataReceivedAt;
    private String iconID;
    private String lat;
    private String lon;
    private String description;
    private String currentTemp;
    private String feelsLikeTemp;
    private String minTemp;
    private String maxTemp;
    private String pressure;
    private String pressureSeaLevel;
    private String pressureGroundLevel;
    private String humidity;
    private String windSpeed;
    private String windDirectionInDegrees;
    private String windGust;
    private String cloudinessPercentage;
    private String sunriseTime;
    private String sunsetTime;
    private String countryID;
    private String cityName;
    private String visibility;

    public String getIconID() {
        return iconID;
    }

    public void setIconID(String iconID) {
        this.iconID = iconID;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getDataReceivedAt() {
        return dataReceivedAt;
    }

    public void setDataReceivedAt(String dataReceivedAt) {
        this.dataReceivedAt = dataReceivedAt;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getFeelsLikeTemp() {
        return feelsLikeTemp;
    }

    public void setFeelsLikeTemp(String feelsLikeTemp) {
        this.feelsLikeTemp = feelsLikeTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getPressureSeaLevel() {
        return pressureSeaLevel;
    }

    public void setPressureSeaLevel(String pressureSeaLevel) {
        this.pressureSeaLevel = pressureSeaLevel;
    }

    public String getPressureGroundLevel() {
        return pressureGroundLevel;
    }

    public void setPressureGroundLevel(String pressureGroundLevel) {
        this.pressureGroundLevel = pressureGroundLevel;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirectionInDegrees() {
        return windDirectionInDegrees;
    }

    public void setWindDirectionInDegrees(String windDirectionInDegrees) {
        this.windDirectionInDegrees = windDirectionInDegrees;
    }

    public String getWindGust() {
        return windGust;
    }

    public void setWindGust(String windGust) {
        this.windGust = windGust;
    }

    public String getCloudinessPercentage() {
        return cloudinessPercentage;
    }

    public void setCloudinessPercentage(String cloudinessPercentage) {
        this.cloudinessPercentage = cloudinessPercentage;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(String sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    @Override
    public String toString() {
        return "WeatherDataModel{" +
                "dataReceivedAt='" + dataReceivedAt + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", description='" + description + '\'' +
                ", currentTemp='" + currentTemp + '\'' +
                ", feelsLikeTemp='" + feelsLikeTemp + '\'' +
                ", minTemp='" + minTemp + '\'' +
                ", maxTemp='" + maxTemp + '\'' +
                ", pressure='" + pressure + '\'' +
                ", pressureSeaLevel='" + pressureSeaLevel + '\'' +
                ", pressureGroundLevel='" + pressureGroundLevel + '\'' +
                ", humidity='" + humidity + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", windDirectionInDegrees='" + windDirectionInDegrees + '\'' +
                ", windGust='" + windGust + '\'' +
                ", cloudinessPercentage='" + cloudinessPercentage + '\'' +
                ", sunriseTime='" + sunriseTime + '\'' +
                ", sunsetTime='" + sunsetTime + '\'' +
                ", countryID='" + countryID + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
