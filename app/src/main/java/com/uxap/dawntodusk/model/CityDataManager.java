package com.uxap.dawntodusk.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class CityDataManager {
    private Context context;

    public CityDataManager(Context context) {
        this.context = context;
    }

    public void saveCityData(String cityName, LatLng latLong){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                String strLatLng = new Gson().toJson(latLong);
                editor.putString(cityName.trim(), strLatLng);
                Log.i("THREAD_SAVE_DATA", Thread.currentThread().getName());
                editor.apply();
            }
        }).start();
    }

    public ArrayList<CityDetailsModel> retrieveCityData() throws InterruptedException {
        Gson gson = new Gson();
        ArrayList<CityDetailsModel> responseData = new ArrayList<>();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Map<String, ?> mappedData = sharedPreferences.getAll();

        for(Map.Entry<String,?> entry: mappedData.entrySet()) {
            LatLng latLng = gson.fromJson((String) entry.getValue(), LatLng.class);
            CityDetailsModel cityDetail = new CityDetailsModel(entry.getKey(), latLng);
            responseData.add(cityDetail);
            Log.i("THREAD_RET_DATA", Thread.currentThread().getName());
        }
        return responseData;
    }

    public void removeCityData(String string){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(string).apply();
    }
}
