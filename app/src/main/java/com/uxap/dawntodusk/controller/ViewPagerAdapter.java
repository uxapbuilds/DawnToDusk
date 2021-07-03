package com.uxap.dawntodusk.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.uxap.dawntodusk.fragment.WeatherPageFragment;
import com.uxap.dawntodusk.model.CityDetailsModel;
import com.uxap.dawntodusk.model.CityDataManager;
import com.uxap.dawntodusk.util.WeatherDataUtils;

import java.util.ArrayList;


public class ViewPagerAdapter extends FragmentStateAdapter {
    public final static String CITY_NAME = "cityName";
    public final static String CITY_LAT_LNG = "cityLatLng";
    public final static String CURRENT_LOCATION = "current location";
    Context context;
    ArrayList<CityDetailsModel> cityDetails = new ArrayList<>();
    CityDataManager cityDataManager;
    WeatherDataUtils weatherDataUtils;


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<CityDetailsModel> cityDetailsModels) {
        super(fragmentActivity);
        this.context = fragmentActivity;

        cityDataManager = new CityDataManager(context);
        weatherDataUtils = new WeatherDataUtils((Activity)context);

        if(cityDetailsModels!=null && cityDetailsModels.size()>0) {
            cityDetails.addAll(cityDetailsModels);
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        Fragment weatherPageFragment = new WeatherPageFragment((Activity)context);

        Bundle bundle = new Bundle();
        bundle.putString(CITY_NAME, cityDetails.get(position).getCityName());
        bundle.putSerializable(CITY_LAT_LNG, cityDetails.get(position).getCityLatLong());
        weatherPageFragment.setArguments(bundle);

        return weatherPageFragment;
    }

    @Override
    public int getItemCount() {
        return cityDetails.size();
    }

    public void addDetailToArray(CityDetailsModel cityDetailsModel){
        if(cityDetailsModel!=null){
            cityDetails.add(cityDetailsModel);
            notifyDataSetChanged();
        }
    }

}
