package com.uxap.dawntodusk.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.uxap.dawntodusk.model.SevenDayForecastDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class SevenDayForecastRefactorer {

    private static final String RECEIVED_DATETIME = "received_date";
    private static final String S_TIME = "s_time";

    public Map<Integer,SevenDayForecastDataModel> getRefactoredForecastDataModel(JSONObject jsonResponse) throws JSONException {
        Map<Integer, SevenDayForecastDataModel> forecastMappedData = new HashMap<>();
        JSONArray forecastDataArray = null;


        if(jsonResponse.has("daily")) {
            forecastDataArray = jsonResponse.getJSONArray("daily");
        }

        if(forecastDataArray != null) {
            for (int i = 0; i < forecastDataArray.length(); i++){
                SevenDayForecastDataModel forecastDataModel = new SevenDayForecastDataModel();
                JSONObject forecastData = forecastDataArray.getJSONObject(i);
                JSONObject tempData = forecastData.getJSONObject("temp");
                JSONObject flTempData = forecastData.getJSONObject("feels_like");
                JSONObject weatherSubData = forecastData.getJSONArray("weather").getJSONObject(0);

                if(forecastData.has("dt")) {
                    forecastDataModel.setDay(millisToDateTime(forecastData.getLong("dt"),RECEIVED_DATETIME));
                    //System.out.println(i+"/"+forecastData.getLong("dt"));
                }else{
                    forecastDataModel.setDay("N/A");
                }

                if(forecastData.has("sunrise")){
                    forecastDataModel.setSunriseTime(millisToDateTime(forecastData.getInt("sunrise"), S_TIME));
                }else{
                    forecastDataModel.setSunriseTime("N/A");
                }

                if(forecastData.has("sunset")){
                    forecastDataModel.setSunsetTime(millisToDateTime(forecastData.getInt("sunset"), S_TIME));
                }else{
                    forecastDataModel.setSunriseTime("N/A");
                }

                if(forecastData.has("moonrise")){
                    forecastDataModel.setMoonriseTime(millisToDateTime(forecastData.getInt("moonrise"), S_TIME));
                }else{
                    forecastDataModel.setMoonriseTime("N/A");
                }

                if(forecastData.has("moonset")){
                    forecastDataModel.setMoonsetTime(millisToDateTime(forecastData.getInt("moonset"), S_TIME));
                }else{
                    forecastDataModel.setMoonsetTime("N/A");
                }

                if(tempData != null) {

                    if (tempData.has("day")) {
                        forecastDataModel.setTempAtDay(tempData.getDouble("day") + "");
                    } else {
                        forecastDataModel.setTempAtDay("N/A");
                    }

                    if (tempData.has("min")) {
                        forecastDataModel.setTempMin(tempData.getDouble("min") + "");
                    } else {
                        forecastDataModel.setTempMin("N/A");
                    }

                    if (tempData.has("max")) {
                        forecastDataModel.setTempMax(tempData.getDouble("max") + "");
                    } else {
                        forecastDataModel.setTempMax("N/A");
                    }

                    if (tempData.has("night")) {
                        forecastDataModel.setTempAtNight(tempData.getDouble("night") + "");
                    } else {
                        forecastDataModel.setTempAtNight("N/A");
                    }

                    if (tempData.has("eve")) {
                        forecastDataModel.setTempEve(tempData.getDouble("eve") + "");
                    } else {
                        forecastDataModel.setTempEve("N/A");
                    }

                    if (tempData.has("morn")) {
                        forecastDataModel.setTempMorn(tempData.getDouble("morn") + "");
                    } else {
                        forecastDataModel.setTempMorn("N/A");
                    }
                }

                if(flTempData != null) {

                    if (flTempData.has("day")) {
                        forecastDataModel.setFeelsLikeTempDay(flTempData.getDouble("day") + "");
                    } else {
                        forecastDataModel.setFeelsLikeTempDay("N/A");
                    }

                    if (flTempData.has("night")) {
                        forecastDataModel.setFeelsLikeTempNight(flTempData.getDouble("night") + "");
                    } else {
                        forecastDataModel.setFeelsLikeTempNight("N/A");
                    }

                    if (flTempData.has("eve")) {
                        forecastDataModel.setFeelsLikeTempEve(flTempData.getDouble("eve") + "");
                    } else {
                        forecastDataModel.setFeelsLikeTempEve("N/A");
                    }

                    if (flTempData.has("morn")) {
                        forecastDataModel.setFeelsLikeTempMorn(flTempData.getDouble("morn") + "");
                    } else {
                        forecastDataModel.setFeelsLikeTempMorn("N/A");
                    }

                }

                if (forecastData.has("pressure")) {
                    forecastDataModel.setPressure(forecastData.getDouble("pressure") + "");
                } else {
                    forecastDataModel.setPressure("N/A");
                }

                if (forecastData.has("humidity")) {
                    forecastDataModel.setHumidity(forecastData.getDouble("humidity") + "");
                } else {
                    forecastDataModel.setHumidity("N/A");
                }

                if (forecastData.has("dew_point")) {
                    forecastDataModel.setDewPoint(forecastData.getDouble("dew_point") + "");
                } else {
                    forecastDataModel.setDewPoint("N/A");
                }

                if (forecastData.has("wind_speed")) {
                    forecastDataModel.setWindSpeed(forecastData.getDouble("wind_speed") + "");
                } else {
                    forecastDataModel.setWindSpeed("N/A");
                }

                if (forecastData.has("wind_deg")) {
                    forecastDataModel.setWindDeg(forecastData.getDouble("wind_deg") + "");
                } else {
                    forecastDataModel.setWindDeg("N/A");
                }

                if (weatherSubData.has("description")) {
                    forecastDataModel.setWeatherDescription(weatherSubData.getString("description") + "");
                } else {
                    forecastDataModel.setWeatherDescription("N/A");
                }

                if (weatherSubData.has("icon")) {
                    forecastDataModel.setIconID(weatherSubData.getString("icon") + "");
                }

                if(forecastData.has("clouds")){
                    forecastDataModel.setCloudPer(forecastData.getInt("clouds")+"");
                }else{
                    forecastDataModel.setCloudPer("N/A");
                }

                if(forecastData.has("uvi")){
                    forecastDataModel.setUvIndex(forecastData.getDouble("uvi")+"");
                }else{
                    forecastDataModel.setUvIndex("N/A");
                }

                forecastMappedData.put(i,forecastDataModel);
            }
        }
        return forecastMappedData;
    }

    public String millisToDateTime(long milisec, String setting){

        if(setting.equalsIgnoreCase(RECEIVED_DATETIME)){
            DateFormat receivedDate = new SimpleDateFormat("E");
            Date result = new Date(milisec*1000L);
            receivedDate.setTimeZone(TimeZone.getDefault());
            return receivedDate.format(result);

        }else if (setting.equalsIgnoreCase(S_TIME)){
            DateFormat s_time = new SimpleDateFormat("hh:mm a");
            Date result = new Date(milisec*1000);
            s_time.setTimeZone(TimeZone.getDefault());
            return s_time.format(result);
        }
        return "N/A";
    }
}
