package views;

import android.app.Activity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.otavioaugusto.myapplication.R;

import java.util.List;

import adapters.ForecastDetailsAdapter;
import models.Forecast;

public class ForecastDetailView {

    private List<Forecast> forecasts;
    private Activity activity;
    private ForecastDetailsAdapter adapter;

    public ForecastDetailView(List<Forecast> forecasts, Activity activity) {
        this.forecasts = forecasts;
        this.activity = activity;
    }

    public void init(int resource, String cityName, String date) {
        setCityName(cityName);
        setLastTimeUpdated(date);
        adapter = new ForecastDetailsAdapter(activity, resource, activity.getLayoutInflater());
        ListView forecastList = (ListView) activity.findViewById(resource);
        forecastList.setAdapter(adapter);
        populateView();
    }

    public void setCityName(String cityName) {
        TextView tView = (TextView) activity.findViewById(R.id.forecast_city_name);
        tView.setText(cityName);
    }

    public void setLastTimeUpdated(String date) {
        TextView tView = (TextView) activity.findViewById(R.id.last_time_updated);
        tView.setText("Last time updated: " + date);
    }

    private void populateView() {
        adapter.setItens(forecasts);
    }

}
