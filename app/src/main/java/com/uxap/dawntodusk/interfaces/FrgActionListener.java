package com.uxap.dawntodusk.interfaces;

import com.uxap.dawntodusk.model.CityDetailsModel;

public interface FrgActionListener {
    void onAddCity(CityDetailsModel cityDetailsModel);
    void onDeleteCity(String cityName);
}
