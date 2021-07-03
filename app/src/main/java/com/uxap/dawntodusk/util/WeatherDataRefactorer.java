package com.uxap.dawntodusk.util;

import android.util.Log;

import com.uxap.dawntodusk.model.WeatherDataModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherDataRefactorer {

    private static final String RECEIVED_DATETIME = "received_date";
    private static final String S_TIME = "s_time";

    public WeatherDataModel getRefactoredWeatherDataModel(JSONObject jsonResponse) throws JSONException {
        Log.i("RESPONSE_TO_REFACTORED", jsonResponse.toString());
        WeatherDataModel weatherDataModel = new WeatherDataModel();

        if(jsonResponse.has("dt")) {
            weatherDataModel.setDataReceivedAt(millisToDateTime(jsonResponse.getLong("dt"), RECEIVED_DATETIME));
        }else{
            weatherDataModel.setDataReceivedAt("--");
        }

        if(jsonResponse.getJSONObject("coord").has("lat")) {
            weatherDataModel.setLat(String.valueOf(jsonResponse.getJSONObject("coord").getDouble("lat")));
        }else{
            weatherDataModel.setLat("--");
        }

        weatherDataModel.setLon(String.valueOf(jsonResponse.getJSONObject("coord").getDouble("lon")));

        weatherDataModel.setDescription(jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description"));

        weatherDataModel.setIconID(jsonResponse.getJSONArray("weather").getJSONObject(0).getString("icon"));

        weatherDataModel.setCurrentTemp(String.valueOf(Math.round(jsonResponse.getJSONObject("main").getDouble("temp"))));

        weatherDataModel.setFeelsLikeTemp(String.valueOf(jsonResponse.getJSONObject("main").getDouble("feels_like")));

        weatherDataModel.setMinTemp(String.valueOf(jsonResponse.getJSONObject("main").getDouble("temp_min")));

        weatherDataModel.setMaxTemp(String.valueOf(jsonResponse.getJSONObject("main").getDouble("temp_max")));

        weatherDataModel.setPressure(String.valueOf(jsonResponse.getJSONObject("main").getInt("pressure")));

        if(jsonResponse.getJSONObject("main").has("sea_level")) {
            weatherDataModel.setPressureSeaLevel(String.valueOf(jsonResponse.getJSONObject("main").getInt("sea_level")));
        }else{
            weatherDataModel.setPressureSeaLevel("--");
        }

        if(jsonResponse.getJSONObject("main").has("grnd_level")) {
            weatherDataModel.setPressureGroundLevel(String.valueOf(jsonResponse.getJSONObject("main").getInt("grnd_level")));
        }else{
            weatherDataModel.setPressureGroundLevel("--");
        }
        weatherDataModel.setHumidity(String.valueOf(jsonResponse.getJSONObject("main").getInt("humidity")));

        weatherDataModel.setWindSpeed(String.valueOf(jsonResponse.getJSONObject("wind").getDouble("speed")));

        weatherDataModel.setWindDirectionInDegrees(String.valueOf(jsonResponse.getJSONObject("wind").getInt("deg")));

        if(jsonResponse.getJSONObject("wind").has("gust")) {
            weatherDataModel.setWindGust(String.valueOf(jsonResponse.getJSONObject("wind").getDouble("gust")));
        }else{
            weatherDataModel.setWindGust("--");
        }

        weatherDataModel.setCloudinessPercentage(String.valueOf(jsonResponse.getJSONObject("clouds").getInt("all")));

        weatherDataModel.setSunriseTime(millisToDateTime(jsonResponse.getJSONObject("sys").getLong("sunrise"),S_TIME));

        weatherDataModel.setSunsetTime(millisToDateTime(jsonResponse.getJSONObject("sys").getLong("sunset"),S_TIME));

        weatherDataModel.setCountryID(jsonResponse.getJSONObject("sys").getString("country"));

        weatherDataModel.setCityName(jsonResponse.getString("name"));

        weatherDataModel.setVisibility(String.valueOf(jsonResponse.getInt("visibility")/1000));

        return weatherDataModel;
    }

    public String millisToDateTime(long milisec, String setting){

        if(setting.equalsIgnoreCase(RECEIVED_DATETIME)){
            DateFormat receivedDate = new SimpleDateFormat("E dd, hh:mm a");
            Date result = new Date(milisec*1000);
            return receivedDate.format(result);
        }else if (setting.equalsIgnoreCase(S_TIME)){
            DateFormat s_time = new SimpleDateFormat("hh:mm:ss a");
            Date result = new Date(milisec*1000);
            return s_time.format(result);
        }
        return "N/A";
    }



}
