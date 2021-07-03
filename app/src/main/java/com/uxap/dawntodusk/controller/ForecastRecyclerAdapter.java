package com.uxap.dawntodusk.controller;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.uxap.dawntodusk.R;
import com.uxap.dawntodusk.model.SevenDayForecastDataModel;
import com.uxap.dawntodusk.view.ForecastRecyclerViewHolder;

import java.util.HashMap;
import java.util.Map;

public class ForecastRecyclerAdapter extends RecyclerView.Adapter<ForecastRecyclerViewHolder> {
    private Map<Integer, SevenDayForecastDataModel> forecastMappedData;
    private boolean isExtraOpen = false;
    private Context context;

    public ForecastRecyclerAdapter(Map<Integer, SevenDayForecastDataModel> recyclerData, Context context) {
        this.forecastMappedData = recyclerData;
        this.context = context;
        System.out.println(recyclerData);
    }

    @NonNull
    @Override
    public ForecastRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frg_main_recycler_item_layout, parent, false);
        return new ForecastRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastRecyclerViewHolder holder, int position) {
        SevenDayForecastDataModel forecastData = forecastMappedData.get(position);
        if(position == 0){
            holder.getTxtDay().setText("Today");
        }else{
            holder.getTxtDay().setText(forecastData.getDay());
        }

        holder.getTxtMaxTemp().setText(forecastData.getTempMax()+"°C");
        holder.getTxtMinTemp().setText(forecastData.getTempMin()+"°C");
        holder.getTxtSunriseTime().setText(forecastData.getSunriseTime());
        holder.getTxtSunsetTime().setText(forecastData.getSunsetTime());
        holder.getTxtMoonriseTime().setText(forecastData.getMoonriseTime());
        holder.getTxtMoonsetTime().setText(forecastData.getMoonsetTime());
        holder.getTxtTempMorn().setText(forecastData.getTempMorn()+"°C");
        holder.getTxtTempDay().setText(forecastData.getTempAtDay()+"°C");
        holder.getTxtTempEve().setText(forecastData.getTempEve()+"°C");
        holder.getTxtTempNight().setText(forecastData.getTempAtNight()+"°C");
        holder.getTxtDescription().setText(forecastData.getWeatherDescription());
        holder.getTxtHumidity().setText(forecastData.getHumidity()+" %");
        holder.getTxtPressure().setText(forecastData.getPressure()+" hpa");
        holder.getTxtUVI().setText(forecastData.getUvIndex());
        holder.getTxtWindDirection().setText(forecastData.getWindDeg()+"°");
        holder.getTxtWindSpeed().setText(forecastData.getWindSpeed()+" km/h");
        holder.getTxtFLMorn().setText("("+forecastData.getFeelsLikeTempMorn()+"°C)");
        holder.getTxtFLDay().setText("("+forecastData.getFeelsLikeTempDay()+"°C)");
        holder.getTxtFLEve().setText("("+forecastData.getFeelsLikeTempEve()+"°C)");
        holder.getTxtFLNight().setText("("+forecastData.getFeelsLikeTempNight()+"°C)");


        holder.getIbOnExpand().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isExtraOpen){
                    holder.getlExtra().setVisibility(View.GONE);
                    isExtraOpen = false;
                    holder.getIbOnExpand().setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }else{
                    holder.getlExtra().setVisibility(View.VISIBLE);
                    isExtraOpen = true;
                    holder.getIbOnExpand().setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
                }
            }
        });

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_error_outline_24);

        String iconURL = "https://openweathermap.org/img/wn/"+forecastData.getIconID()+"@2x.png";
        Glide.with(context).load(iconURL).apply(options).into(holder.getIvWeatherImage());

    }

    @Override
    public int getItemCount() {
        return forecastMappedData.size();
    }
}
