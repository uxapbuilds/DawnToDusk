package com.uxap.dawntodusk.util;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.location.LocationServices;
import com.uxap.dawntodusk.interfaces.FrgActionListener;
import com.uxap.dawntodusk.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.google.android.gms.location.FusedLocationProviderClient;

public class WeatherDataUtils {
    private Activity activity;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private FrgActionListener frgActionListener;

    public WeatherDataUtils(Activity activity) {
        this.activity = activity;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        frgActionListener = (FrgActionListener) activity;
    }

    public LatLng getLatLongByName(String city) throws InterruptedException {
        final LatLng[] latLng = new LatLng[1];
        final CountDownLatch latch = new CountDownLatch(1);
        Geocoder gc = new Geocoder(activity.getBaseContext());
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (gc.isPresent()) {
                    List<Address> list = null;
                    try {
                        list = gc.getFromLocationName(city, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (list != null && list.size() != 0) {
                        Address address = list.get(0);
                        Log.i("Thread: ", Thread.currentThread().getName());
                        latLng[0] = new LatLng(address.getLatitude(), address.getLongitude());
                        latch.countDown();

                    } else {
                        //if city lat long couldn't be found set latLng to null(null check is in mainActivity).
                        //latch countdown has to be initiated anyway as latch.await will keep waiting for countdown to end.
                        Log.i("Thread: ", Thread.currentThread().getName());
                        latLng[0] = null;
                        latch.countDown();
                    }
                }
            }
        }).start();
        latch.await();
        return latLng[0];
    }
}
