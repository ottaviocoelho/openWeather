package com.example.otavioaugusto.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import controllers.ForecastDetailsController;

public class ForecastDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_detail);
        int sId = getIntent().getIntExtra("id", 0);
        Long id = Long.valueOf(sId);
        new ForecastDetailsController(this, R.id.forecast_list_view, id);
    }
}
