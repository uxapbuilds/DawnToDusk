package com.uxap.dawntodusk.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.uxap.dawntodusk.R;
import com.uxap.dawntodusk.controller.ForecastRecyclerAdapter;
import com.uxap.dawntodusk.controller.ViewPagerAdapter;
import com.uxap.dawntodusk.interfaces.FrgActionListener;
import com.uxap.dawntodusk.model.CityDetailsModel;
import com.uxap.dawntodusk.model.LatLng;
import com.uxap.dawntodusk.model.SevenDayForecastDataModel;
import com.uxap.dawntodusk.model.CityDataManager;
import com.uxap.dawntodusk.model.WeatherDataModel;
import com.uxap.dawntodusk.util.SevenDayForecastRefactorer;
import com.uxap.dawntodusk.util.VolleySingleton;
import com.uxap.dawntodusk.util.WeatherDataRefactorer;
import com.uxap.dawntodusk.util.WeatherDataUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class WeatherPageFragment extends Fragment{

    private final static String OPEN_WEATHER_MAPS_API_KEY = "56d113cb6c1bc7866aad28202fd6778f";
    private RecyclerView rvForecastRecycler;
    private Bundle cityDetailBundle = new Bundle();
    private String cityName;
    private LatLng cityLatLng;
    private RequestQueue requestQueue;
    private Activity activity;
    private WeatherDataUtils weatherDataUtils;
    private WeatherDataRefactorer weatherDataRefactoring;
    private SevenDayForecastRefactorer sevenDayForecastRefactorer;
    private CityDataManager cityDataManager;

    private TextView txtCityName, txtTimeFetched, txtWeatherStatusText, txtCurrentTemp, txtMinTemp,txtMaxTemp, txtPressure,
            txtWind, txtFeelsLike, txtHumidity, txtCloudiness, txtVisibility, txtCountryCode, txtSunsetTime, txtSunriseTime;
    private ImageView ivWeatherStatusImage, ivCurrentLocation;

    private ImageButton ibOptions;

    private FrgActionListener frgActionListener;


    public WeatherPageFragment() {
        // Required empty public constructor
    }

    public WeatherPageFragment(Activity activity){
        this.activity = activity;
    }

    public static WeatherPageFragment newInstance(Activity activity) {
        WeatherPageFragment fragment = new WeatherPageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cityDetailBundle = getArguments();
        cityName = cityDetailBundle.getString(ViewPagerAdapter.CITY_NAME);
        cityLatLng = (LatLng) cityDetailBundle.getSerializable(ViewPagerAdapter.CITY_LAT_LNG);

        requestQueue = VolleySingleton.getInstance().getRequestQueue();

        weatherDataRefactoring = new WeatherDataRefactorer();
        sevenDayForecastRefactorer = new SevenDayForecastRefactorer();
        weatherDataUtils = new WeatherDataUtils(getActivity());
        cityDataManager = new CityDataManager(getContext());

        Log.i("CITY_DETAILS", cityName+"/"+cityLatLng.toString());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        frgActionListener = (FrgActionListener)context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ibOptions = view.findViewById(R.id.ibOptions);

        String CURRENT_WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?"+
                "lat="+cityLatLng.getLattitude()
                +"&lon="+cityLatLng.getLongitude()
                +"&units=metric&"
                +"appid="+OPEN_WEATHER_MAPS_API_KEY;

        String SEVEN_DAY_FORECAST_API_URL = "https://api.openweathermap.org/data/2.5/onecall?"
                +"lat="+cityLatLng.getLattitude()
                +"&lon="+cityLatLng.getLongitude()
                +"&units=metric"
                +"&exclude=current,hourly,minutely"
                +"&appid="+OPEN_WEATHER_MAPS_API_KEY;

        PopupMenu optionsMenu = new PopupMenu(getActivity(), ibOptions);
        optionsMenu.getMenuInflater().inflate(R.menu.popup_options_layout, optionsMenu.getMenu());

        ibOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.pmAddCity){
                            showAddCityDialog();
                        }else if(item.getItemId() == R.id.pmDeleteCity){
                            deleteCity(cityName);
                        }
                        return false;
                    }
                });
                optionsMenu.show();
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                JsonObjectRequest weatherDataJsonRequest = new JsonObjectRequest(Request.Method.GET, CURRENT_WEATHER_API_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("cod") == 200) {
                                WeatherDataModel weatherDataModel = weatherDataRefactoring.getRefactoredWeatherDataModel(response);
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        setWeatherDataToViews(view, weatherDataModel);
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            Log.e("JSON_RESPONSE_ERROR_W", e.getMessage()+"\n"+e.getStackTrace().toString());

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("JSON_REQUEST_ERROR", error.getMessage()+"\n"+error.getStackTrace().toString());
                    }
                });
                requestQueue.add(weatherDataJsonRequest);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                JsonObjectRequest sevenDayForecastDataJsonRequest = new JsonObjectRequest(Request.Method.GET, SEVEN_DAY_FORECAST_API_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {
                            try {
                                Map<Integer, SevenDayForecastDataModel> forecastMappedDataModels = sevenDayForecastRefactorer.getRefactoredForecastDataModel(response);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Map<Integer, SevenDayForecastDataModel> forecastMappedDataModels = new HashMap<>();
                                        try {
                                            forecastMappedDataModels = sevenDayForecastRefactorer.getRefactoredForecastDataModel(response);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

                                        rvForecastRecycler = view.findViewById(R.id.frgForecastRecycler);
                                        rvForecastRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                                        rvForecastRecycler.setAdapter(new ForecastRecyclerAdapter(forecastMappedDataModels, getContext()));

                                    }
                                });
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("JSON_REQUEST_ERROR", error.getMessage()+"\n"+error.getStackTrace().toString());
                    }
                });
                requestQueue.add(sevenDayForecastDataJsonRequest);
            }
        }).start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_page, container, false);
    }

    private void setWeatherDataToViews(View view, WeatherDataModel weatherDataModel) {
        if(view!=null){

            txtCityName = view.findViewById(R.id.frgCityName);
            txtCountryCode = view.findViewById(R.id.frgCountryCode);
            txtTimeFetched = view.findViewById(R.id.frgTimeFetched);
            txtCurrentTemp = view.findViewById(R.id.frgCurrentTemp);
            txtMaxTemp = view.findViewById(R.id.frgMaxTemp);
            txtMinTemp = view.findViewById(R.id.frgMinTemp);
            txtPressure = view.findViewById(R.id.frgDetailPressure);
            txtWind = view.findViewById(R.id.frgDetailWindSpeed);
            txtFeelsLike = view.findViewById(R.id.frgDetailFeelsLike);
            txtCloudiness = view.findViewById(R.id.frgDetaiCloudiness);
            txtVisibility = view.findViewById(R.id.frgDetailVisibility);
            txtHumidity = view.findViewById(R.id.frgDetailHumidity);
            txtWeatherStatusText = view.findViewById(R.id.frgWeatherStatusText);
            ivWeatherStatusImage = view.findViewById(R.id.frgWeatherStatusImage);
            ivCurrentLocation = view.findViewById(R.id.frgCurrentLocationImage);
            txtSunriseTime = view.findViewById(R.id.frgSunriseTime);
            txtSunsetTime = view.findViewById(R.id.frgSunsetTime);

            txtCityName.setText(weatherDataModel.getCityName()+",");
            txtCountryCode.setText(weatherDataModel.getCountryID());

            txtPressure.setText(weatherDataModel.getPressure()+" hPa");
            txtHumidity.setText(weatherDataModel.getHumidity()+"%");
            txtCloudiness.setText(weatherDataModel.getCloudinessPercentage()+"%");
            txtVisibility.setText(weatherDataModel.getVisibility()+"km");
            txtFeelsLike.setText(weatherDataModel.getFeelsLikeTemp()+"°C");
            txtWind.setText(weatherDataModel.getWindSpeed()+"km");

            txtTimeFetched.setText(weatherDataModel.getDataReceivedAt()+" IST");
            txtWeatherStatusText.setText(weatherDataModel.getDescription());
            txtCurrentTemp.setText(weatherDataModel.getCurrentTemp()+"°");
            txtMaxTemp.setText(weatherDataModel.getMaxTemp()+"° C");
            txtMinTemp.setText(weatherDataModel.getMinTemp()+"° C");

            txtSunriseTime.setText(weatherDataModel.getSunriseTime());
            txtSunsetTime.setText(weatherDataModel.getSunsetTime());

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.ic_baseline_image_24)
                    .error(R.drawable.ic_baseline_error_outline_24);

            String iconURL = "https://openweathermap.org/img/wn/"+weatherDataModel.getIconID()+"@2x.png";
            Glide.with(this).load(iconURL).apply(options).into(ivWeatherStatusImage);
        }
    }

    private void showAddCityDialog() {
        Dialog addCityDialog = new Dialog(getActivity());
        addCityDialog.setContentView(R.layout.dialog_add_city);
        addCityDialog.getWindow().setBackgroundDrawable(getActivity().getDrawable(R.drawable.dialog_background));
        addCityDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addCityDialog.getWindow().getAttributes().windowAnimations = R.style.baseAnimation;
        EditText edtNewCityName = addCityDialog.findViewById(R.id.edtCityName);
        Button btnAddCity = addCityDialog.findViewById(R.id.btnAddCity);

        btnAddCity.setOnClickListener(new View.OnClickListener() {
            LatLng latLng;
            @Override
            public void onClick(View v) {
                String cityName = edtNewCityName.getText().toString().trim();

                if(cityName.length()>40){
                    Toast.makeText(getContext(), "Max Length for City name exceeded, must be under 40 Chars.", Toast.LENGTH_LONG).show();
                }else if(cityName.equalsIgnoreCase("")) {
                    Toast.makeText(getContext(), "City name can't be Empty. Please put a name.", Toast.LENGTH_LONG).show();
                }else if(!Pattern.matches("^[a-zA-Z ]*$", cityName)){
                    Toast.makeText(getContext(), "City name must contain letters only.", Toast.LENGTH_LONG).show();
                }else{
                    try {
                        latLng = weatherDataUtils.getLatLongByName(cityName);
                    } catch (InterruptedException e) {
                        Log.e("GEOCODER_EXP", e.getMessage()+"\n"+e.getStackTrace());
                    }

                    if(latLng!=null){
                        cityDataManager.saveCityData(cityName, latLng);
                        CityDetailsModel cityDetailsModel = new CityDetailsModel(edtNewCityName.getText().toString().trim(), latLng);
                        Toast.makeText(getContext(), cityName+" is added.", Toast.LENGTH_SHORT).show();
                        frgActionListener.onAddCity(cityDetailsModel);
                        addCityDialog.dismiss();

                    }else{
                        Toast.makeText(getContext(), edtNewCityName.getText().toString().trim()+" could not be found!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        addCityDialog.show();
    }

    private void deleteCity(String cityName) {
        frgActionListener.onDeleteCity(cityName);
    }
}