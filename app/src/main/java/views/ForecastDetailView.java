package views;

import android.app.Activity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.otavioaugusto.myapplication.R;

import java.util.List;

import adapters.ForecastDetailsAdapter;
import factory.DetailsBuilder;
import factory.ForecastDayBuilder;
import models.Forecast;
import models.ForecastDay;

public class ForecastDetailView {

    private List<ForecastDay> forecastDays;
    private Activity activity;
    private ForecastDetailsAdapter adapter;

    public ForecastDetailView(Forecast forecast, Activity activity) {
        this.forecastDays = ForecastDayBuilder.build(DetailsBuilder.buildDetails(forecast));
        this.activity = activity;
    }

    public void init(int resource) {
        TextView tView = (TextView) activity.findViewById(R.id.forecast_city_name);
        tView.setText(forecastDays.get(0).getCityName());
        adapter = new ForecastDetailsAdapter(activity, resource, activity.getLayoutInflater());
        ListView forecastList = (ListView) activity.findViewById(resource);
        forecastList.setAdapter(adapter);
        populateView();
    }

    private void populateView() {
        adapter.setItens(forecastDays);
    }

}
