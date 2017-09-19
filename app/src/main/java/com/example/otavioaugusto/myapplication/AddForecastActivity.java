package com.example.otavioaugusto.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import controllers.ForecastAdditionController;
import utils.RequestListener;

public class AddForecastActivity extends AppCompatActivity implements RequestListener {

    private ForecastAdditionController controller = new ForecastAdditionController();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_forecast);
        controller.init(this);
    }

    public void addNewForecast(View view) {
        progressDialog = ProgressDialog.show(this, "teste", "teste", true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                controller.addForecast(AddForecastActivity.this);
            }
        }).run();
    }

    @Override
    public void onSuccess() {
        progressDialog.dismiss();
        finish();
    }

    @Override
    public void onError() {
        new AlertDialog.Builder(this)
                .setTitle("Erro")
                .setMessage("Algo deu errado!")
                .setPositiveButton("Ok", null)
                .show();
        progressDialog.dismiss();
    }
}
