package com.uxap.dawntodusk.view;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.uxap.dawntodusk.R;

public class ForecastRecyclerViewHolder extends RecyclerView.ViewHolder {
    private ImageButton ibOnExpand;
    private TextView txtDay, txtMaxTemp, txtMinTemp, txtSunriseTime, txtSunsetTime, txtMoonriseTime, txtMoonsetTime, txtTempMorn, txtTempDay, txtTempEve, txtTempNight
            ,txtDescription, txtPressure, txtHumidity, txtCloudsPer, txtUVI, txtWindSpeed, txtWindDirection, txtFLMorn, txtFLDay, txtFLEve, txtFLNight;
    private ImageView ivWeatherImage;
    private LinearLayout lExtra, lBase;
    private CardView rvItemCardView;

    public ForecastRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        ibOnExpand = itemView.findViewById(R.id.rvOnExpand);
        txtDay = itemView.findViewById(R.id.rvDayText);
        txtMaxTemp = itemView.findViewById(R.id.rvMaxTemp);
        txtMinTemp = itemView.findViewById(R.id.rvMinTemp);
        txtSunriseTime = itemView.findViewById(R.id.rvSunriseTime);
        txtSunsetTime = itemView.findViewById(R.id.rvSunsetTime);
        txtTempMorn = itemView.findViewById(R.id.rvTempMorn);
        txtTempDay = itemView.findViewById(R.id.rvTempDay);
        txtTempEve = itemView.findViewById(R.id.rvTempEve);
        txtTempNight = itemView.findViewById(R.id.rvTempNight);
        txtMoonriseTime = itemView.findViewById(R.id.rvMoonriseTime);
        txtMoonsetTime = itemView.findViewById(R.id.rvMoonsetTime);
        txtDescription = itemView.findViewById(R.id.rvDescription);
        txtPressure = itemView.findViewById(R.id.rvPressure);
        txtHumidity = itemView.findViewById(R.id.rvHumidity);
        txtCloudsPer = itemView.findViewById(R.id.rvCloudiness);
        txtUVI = itemView.findViewById(R.id.rvUVI);
        txtWindSpeed = itemView.findViewById(R.id.rvWindSpeed);
        txtWindDirection = itemView.findViewById(R.id.rvWindDirection);
        txtFLMorn = itemView.findViewById(R.id.rvFeelsLikeTempMorn);
        txtFLDay = itemView.findViewById(R.id.rvFeelsLikeTempDay);
        txtFLEve = itemView.findViewById(R.id.rvFeelsLikeTempEve);
        txtFLNight = itemView.findViewById(R.id.rvFeelsLikeTempNight);

        ivWeatherImage = itemView.findViewById(R.id.rvWeatherImage);

        lBase = itemView.findViewById(R.id.rv_LinearL_Base);
        lExtra = itemView.findViewById(R.id.rv_LinearL_Extra);
        rvItemCardView = itemView.findViewById(R.id.frg_rv_cv);
    }

    public TextView getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(TextView txtDescription) {
        this.txtDescription = txtDescription;
    }

    public TextView getTxtPressure() {
        return txtPressure;
    }

    public void setTxtPressure(TextView txtPressure) {
        this.txtPressure = txtPressure;
    }

    public TextView getTxtHumidity() {
        return txtHumidity;
    }

    public void setTxtHumidity(TextView txtHumidity) {
        this.txtHumidity = txtHumidity;
    }

    public TextView getTxtCloudsPer() {
        return txtCloudsPer;
    }

    public void setTxtCloudsPer(TextView txtCloudsPer) {
        this.txtCloudsPer = txtCloudsPer;
    }

    public TextView getTxtUVI() {
        return txtUVI;
    }

    public void setTxtUVI(TextView txtUVI) {
        this.txtUVI = txtUVI;
    }

    public TextView getTxtWindSpeed() {
        return txtWindSpeed;
    }

    public void setTxtWindSpeed(TextView txtWindSpeed) {
        this.txtWindSpeed = txtWindSpeed;
    }

    public TextView getTxtWindDirection() {
        return txtWindDirection;
    }

    public void setTxtWindDirection(TextView txtWindDirection) {
        this.txtWindDirection = txtWindDirection;
    }

    public TextView getTxtFLMorn() {
        return txtFLMorn;
    }

    public void setTxtFLMorn(TextView txtFLMorn) {
        this.txtFLMorn = txtFLMorn;
    }

    public TextView getTxtFLDay() {
        return txtFLDay;
    }

    public void setTxtFLDay(TextView txtFLDay) {
        this.txtFLDay = txtFLDay;
    }

    public TextView getTxtFLEve() {
        return txtFLEve;
    }

    public void setTxtFLEve(TextView txtFLEve) {
        this.txtFLEve = txtFLEve;
    }

    public TextView getTxtFLNight() {
        return txtFLNight;
    }

    public void setTxtFLNight(TextView txtFLNight) {
        this.txtFLNight = txtFLNight;
    }

    public TextView getTxtMoonriseTime() {
        return txtMoonriseTime;
    }

    public void setTxtMoonriseTime(TextView txtMoonriseTime) {
        this.txtMoonriseTime = txtMoonriseTime;
    }

    public TextView getTxtMoonsetTime() {
        return txtMoonsetTime;
    }

    public void setTxtMoonsetTime(TextView txtMoonsetTime) {
        this.txtMoonsetTime = txtMoonsetTime;
    }

    public TextView getTxtSunriseTime() {
        return txtSunriseTime;
    }

    public void setTxtSunriseTime(TextView txtSunriseTime) {
        this.txtSunriseTime = txtSunriseTime;
    }

    public TextView getTxtSunsetTime() {
        return txtSunsetTime;
    }

    public void setTxtSunsetTime(TextView txtSunsetTime) {
        this.txtSunsetTime = txtSunsetTime;
    }

    public TextView getTxtTempMorn() {
        return txtTempMorn;
    }

    public void setTxtTempMorn(TextView txtTempMorn) {
        this.txtTempMorn = txtTempMorn;
    }

    public TextView getTxtTempDay() {
        return txtTempDay;
    }

    public void setTxtTempDay(TextView txtTempDay) {
        this.txtTempDay = txtTempDay;
    }

    public TextView getTxtTempEve() {
        return txtTempEve;
    }

    public void setTxtTempEve(TextView txtTempEve) {
        this.txtTempEve = txtTempEve;
    }

    public TextView getTxtTempNight() {
        return txtTempNight;
    }

    public void setTxtTempNight(TextView txtTempNight) {
        this.txtTempNight = txtTempNight;
    }

    public CardView getRvItemCv() {
        return rvItemCardView;
    }

    public void setRvItemCv(CardView rvItemCardView) {
        this.rvItemCardView = rvItemCardView;
    }

    public ImageButton getIbOnExpand() {
        return ibOnExpand;
    }

    public void setIbOnExpand(ImageButton ibOnExpand) {
        this.ibOnExpand = ibOnExpand;
    }

    public TextView getTxtDay() {
        return txtDay;
    }

    public void setTxtDay(TextView txtDay) {
        this.txtDay = txtDay;
    }

    public TextView getTxtMaxTemp() {
        return txtMaxTemp;
    }

    public void setTxtMaxTemp(TextView txtMaxTemp) {
        this.txtMaxTemp = txtMaxTemp;
    }

    public TextView getTxtMinTemp() {
        return txtMinTemp;
    }

    public void setTxtMinTemp(TextView txtMinTemp) {
        this.txtMinTemp = txtMinTemp;
    }

    public ImageView getIvWeatherImage() {
        return ivWeatherImage;
    }

    public void setIvWeatherImage(ImageView ivWeatherImage) {
        this.ivWeatherImage = ivWeatherImage;
    }

    public LinearLayout getlExtra() {
        return lExtra;
    }

    public void setlExtra(LinearLayout lExtra) {
        this.lExtra = lExtra;
    }

    public LinearLayout getlBase() {
        return lBase;
    }

    public void setlBase(LinearLayout lBase) {
        this.lBase = lBase;
    }
}
