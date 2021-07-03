package com.uxap.dawntodusk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uxap.dawntodusk.controller.ViewPagerAdapter;
import com.uxap.dawntodusk.interfaces.FrgActionListener;
import com.uxap.dawntodusk.model.CityDetailsModel;
import com.uxap.dawntodusk.model.LatLng;
import com.uxap.dawntodusk.model.CityDataManager;
import com.uxap.dawntodusk.util.CustomPageTransformer;
import com.uxap.dawntodusk.util.WeatherDataUtils;

import java.io.IOException;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity implements FrgActionListener {
    private WeatherDataUtils weatherDataUtils;
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private CityDataManager cityDataManager;
    private Button btnAddInitialCity;
    private LinearLayout mainLinearLayout;
    private TextView txtMainCompanyName, txtMainAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherDataUtils = new WeatherDataUtils(MainActivity.this);
        cityDataManager = new CityDataManager(this);
        viewPager = findViewById(R.id.main_viewpager);

        btnAddInitialCity = findViewById(R.id.btnAddInitialCity);
        txtMainCompanyName = findViewById(R.id.txtMainCompanyName);
        txtMainAppName = findViewById(R.id.txtMainAppName);
        mainLinearLayout = findViewById(R.id.linearL_main);

        try {

            if (cityDataManager.retrieveCityData().size() > 0) {
                viewPagerAdapter = new ViewPagerAdapter(this, cityDataManager.retrieveCityData());
                viewPager.setAdapter(viewPagerAdapter);

                btnAddInitialCity.setVisibility(View.GONE);
                mainLinearLayout.setVisibility(View.GONE);
                txtMainAppName.setVisibility(View.GONE);
                txtMainCompanyName.setVisibility(View.GONE);

            } else {
                viewPagerAdapter = new ViewPagerAdapter(this, null);
                viewPager.setAdapter(viewPagerAdapter);
            }

        } catch (InterruptedException e) {
            Log.e("INTERRUPT_EXP", e.getStackTrace() + "");
        }
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        viewPager.setPageTransformer(new CustomPageTransformer());

        btnAddInitialCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (cityDataManager.retrieveCityData().size() == 0) {
                        showInitialAddCityDialog();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onAddCity(CityDetailsModel cityDetailsModel) {
        if (cityDetailsModel != null) {
            viewPagerAdapter.addDetailToArray(cityDetailsModel);
            Toast.makeText(MainActivity.this, cityDetailsModel.getCityName() + " is added.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDeleteCity(String cityName) {
        cityDataManager.removeCityData(cityName);

        try {
            if (cityDataManager.retrieveCityData().size() > 0) {
                viewPagerAdapter = new ViewPagerAdapter(this, cityDataManager.retrieveCityData());
                viewPager.setAdapter(viewPagerAdapter);
            } else {
                viewPagerAdapter = new ViewPagerAdapter(this, null);
                viewPager.setAdapter(viewPagerAdapter);

                btnAddInitialCity.setVisibility(View.VISIBLE);
                mainLinearLayout.setVisibility(View.VISIBLE);
                txtMainAppName.setVisibility(View.VISIBLE);
                txtMainCompanyName.setVisibility(View.VISIBLE);
            }
        } catch (InterruptedException e) {
            Log.e("INTERRUPT_EXP", e.getStackTrace() + "");
        }
    }

    private void showInitialAddCityDialog() {
        Dialog addCityDialog = new Dialog(this);
        addCityDialog.setContentView(R.layout.dialog_add_city);
        addCityDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        addCityDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addCityDialog.getWindow().getAttributes().windowAnimations = R.style.baseAnimation;
        EditText edtNewCityName = addCityDialog.findViewById(R.id.edtCityName);
        Button btnAddCity = addCityDialog.findViewById(R.id.btnAddCity);

        btnAddCity.setOnClickListener(new View.OnClickListener() {
            LatLng latLng;

            @Override
            public void onClick(View v) {
                String cityName = edtNewCityName.getText().toString().trim();

                if (cityName.length() > 40) {
                    Toast.makeText(MainActivity.this, "Max Length for City name exceeded, must be under 40 Chars.", Toast.LENGTH_LONG).show();
                } else if (cityName.equalsIgnoreCase("")) {
                    Toast.makeText(MainActivity.this, "City name can't be Empty. Please put a name.", Toast.LENGTH_LONG).show();
                } else if (!Pattern.matches("^[a-zA-Z ]*$", cityName)) {
                    Toast.makeText(MainActivity.this, "City name must contain letters only.", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        latLng = weatherDataUtils.getLatLongByName(cityName);
                    } catch (InterruptedException e) {
                        Log.e("GEOCODER_EXP", e.getMessage() + "\n" + e.getStackTrace());
                    }

                    if (latLng != null) {
                        cityDataManager.saveCityData(cityName, latLng);
                        CityDetailsModel cityDetailsModel = new CityDetailsModel(edtNewCityName.getText().toString().trim(), latLng);
                        viewPagerAdapter.addDetailToArray(cityDetailsModel);
                        Toast.makeText(MainActivity.this, cityDetailsModel.getCityName() + " is added.", Toast.LENGTH_SHORT).show();

                        btnAddInitialCity.setVisibility(View.GONE);
                        mainLinearLayout.setVisibility(View.GONE);
                        txtMainAppName.setVisibility(View.GONE);
                        txtMainCompanyName.setVisibility(View.GONE);

                        addCityDialog.dismiss();

                    } else {
                        Toast.makeText(MainActivity.this, edtNewCityName.getText().toString().trim() + " could not be found!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        addCityDialog.show();
    }
}