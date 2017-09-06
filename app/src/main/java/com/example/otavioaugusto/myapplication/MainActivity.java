package com.example.otavioaugusto.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import controllers.CityListController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CityListController.getInstance().init(this, R.id.city_list_view);
        CityListController.getInstance().addByName("Blumenau,BR");
        CityListController.getInstance().addByName("Joinville,BR");
        CityListController.getInstance().addByName("Criciuma,BR");
    }

    public void newForecast(View view) {
        Intent intent = new Intent(this, AddForecastActivity.class);
        startActivity(intent);
    }

    public void forecastDetail (View view) {
        Intent intent = new Intent(MainActivity.this, ForecastDetailActivity.class);
        int hello = view.getId();
        intent.putExtra("id", hello);
        startActivity(intent);
    }
}
