package com.uxap.dawntodusk.model;

public class SevenDayForecastDataModel {
    private String receivedTime;
    private String day;
    private String iconID;
    private String sunriseTime;
    private String sunsetTime;
    private String moonriseTime;
    private String moonsetTime;
    private String tempAtDay;
    private String tempAtNight;
    private String tempMax;
    private String tempMin;
    private String tempEve;
    private String tempMorn;
    private String feelsLikeTempDay;
    private String feelsLikeTempNight;
    private String feelsLikeTempEve;
    private String feelsLikeTempMorn;
    private String pressure;
    private String humidity;
    private String dewPoint;
    private String windSpeed;
    private String windDeg;
    private String weatherDescription;
    private String cloudPer;
    private String uvIndex;

    public String getIconID() {
        return iconID;
    }

    public void setIconID(String iconID) {
        this.iconID = iconID;
    }

    public String getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(String receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    public String getMoonriseTime() {
        return moonriseTime;
    }

    public void setMoonriseTime(String moonriseTime) {
        this.moonriseTime = moonriseTime;
    }

    public String getMoonsetTime() {
        return moonsetTime;
    }

    public void setMoonsetTime(String moonsetTime) {
        this.moonsetTime = moonsetTime;
    }

    public String getTempAtDay() {
        return tempAtDay;
    }

    public void setTempAtDay(String tempAtDay) {
        this.tempAtDay = tempAtDay;
    }

    public String getTempAtNight() {
        return tempAtNight;
    }

    public void setTempAtNight(String tempAtNight) {
        this.tempAtNight = tempAtNight;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempEve() {
        return tempEve;
    }

    public void setTempEve(String tempEve) {
        this.tempEve = tempEve;
    }

    public String getTempMorn() {
        return tempMorn;
    }

    public void setTempMorn(String tempMorn) {
        this.tempMorn = tempMorn;
    }

    public String getFeelsLikeTempDay() {
        return feelsLikeTempDay;
    }

    public void setFeelsLikeTempDay(String feelsLikeTempDay) {
        this.feelsLikeTempDay = feelsLikeTempDay;
    }

    public String getFeelsLikeTempNight() {
        return feelsLikeTempNight;
    }

    public void setFeelsLikeTempNight(String feelsLikeTempNight) {
        this.feelsLikeTempNight = feelsLikeTempNight;
    }

    public String getFeelsLikeTempEve() {
        return feelsLikeTempEve;
    }

    public void setFeelsLikeTempEve(String feelsLikeTempEve) {
        this.feelsLikeTempEve = feelsLikeTempEve;
    }

    public String getFeelsLikeTempMorn() {
        return feelsLikeTempMorn;
    }

    public void setFeelsLikeTempMorn(String feelsLikeTempMorn) {
        this.feelsLikeTempMorn = feelsLikeTempMorn;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(String dewPoint) {
        this.dewPoint = dewPoint;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(String windDeg) {
        this.windDeg = windDeg;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getCloudPer() {
        return cloudPer;
    }

    public void setCloudPer(String cloudPer) {
        this.cloudPer = cloudPer;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(String uvIndex) {
        this.uvIndex = uvIndex;
    }

    @Override
    public String toString() {
        return "SevenDayForecastDataModel{" +
                "receivedTime='" + receivedTime + '\'' +
                ", day='" + day + '\'' +
                ", sunriseTime='" + sunriseTime + '\'' +
                ", sunsetTime='" + sunsetTime + '\'' +
                ", moonriseTime='" + moonriseTime + '\'' +
                ", moonsetTime='" + moonsetTime + '\'' +
                ", tempAtDay='" + tempAtDay + '\'' +
                ", tempAtNight='" + tempAtNight + '\'' +
                ", tempMax='" + tempMax + '\'' +
                ", tempMin='" + tempMin + '\'' +
                ", tempEve='" + tempEve + '\'' +
                ", tempMorn='" + tempMorn + '\'' +
                ", feelsLikeTempDay='" + feelsLikeTempDay + '\'' +
                ", feelsLikeTempNight='" + feelsLikeTempNight + '\'' +
                ", feelsLikeTempEve='" + feelsLikeTempEve + '\'' +
                ", feelsLikeTempMorn='" + feelsLikeTempMorn + '\'' +
                ", pressure='" + pressure + '\'' +
                ", humidity='" + humidity + '\'' +
                ", dewPoint='" + dewPoint + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", windDeg='" + windDeg + '\'' +
                ", weatherDescription='" + weatherDescription + '\'' +
                ", cloudPer='" + cloudPer + '\'' +
                ", uvIndex='" + uvIndex + '\'' +
                '}';
    }
}
