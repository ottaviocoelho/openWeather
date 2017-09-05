package views;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.otavioaugusto.myapplication.MainActivity;

import java.util.List;

import adapters.CityListAdapter;
import models.City;

public class CityListView {

    private MainActivity activity;
    private List<City> cities;
    private CityListAdapter adapter;

    public CityListView(MainActivity activity, List<City> cities) {
        this.activity = activity;
        this.cities = cities;
    }

    public void init(int resource) {
        adapter = new CityListAdapter(activity, resource, cities, activity.getLayoutInflater());
        ListView cityList = (ListView) activity.findViewById(resource);
        cityList.setAdapter(adapter);
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                activity.forecastDetail(view);
            }
        });
        populateView();
    }

    public void addToView(City city) {
        adapter.add(city);
    }

    public void updateView(List<City> cities) {
        this.cities = cities;
        populateView();
    }

    private void populateView() {
        adapter.clear();
        addAll();
    }

    private void addAll() {
        for(City city : cities) adapter.add(city);
    }

}
