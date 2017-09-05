package views;

import android.app.Activity;
import android.widget.ListView;

import java.util.List;

import adapters.ForecastDetailsAdapter;
import factory.DetailsBuilder;
import models.Details;
import models.Forecast;

public class ForecastDetailView {

    private List<Details> details;
    private Activity activity;
    private ForecastDetailsAdapter adapter;

    public ForecastDetailView(Forecast forecast, Activity activity) {
        this.details = DetailsBuilder.buildDetails(activity, forecast);
        this.activity = activity;
    }

    public void init(int resource) {
        adapter = new ForecastDetailsAdapter(activity, resource, details, activity.getLayoutInflater());
        ListView forecastList = (ListView) activity.findViewById(resource);
        forecastList.setAdapter(adapter);
//        populateView();
    }

//    private void populateView() {
//        adapter.clear();
//        addAll();
//    }
//
//    private void addAll() {
//        for(Details detail : details) adapter.add(detail);
//    }

}
