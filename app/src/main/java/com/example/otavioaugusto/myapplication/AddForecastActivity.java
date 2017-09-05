package com.example.otavioaugusto.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import controllers.ForecastAdditionController;

public class AddForecastActivity extends AppCompatActivity {

    ForecastAdditionController controller = new ForecastAdditionController();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_forecast);
        controller.init(this);
    }

    public void addNewForecast(View view) {
        controller.addForecast();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
