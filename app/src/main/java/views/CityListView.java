package views;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.otavioaugusto.myapplication.MainActivity;
import com.example.otavioaugusto.myapplication.R;

import java.util.List;

import adapters.CityListAdapter;
import models.City;
import utils.CityComparator;
import utils.OrderByEnum;

public class CityListView implements AdapterView.OnItemSelectedListener {

    private MainActivity context;
    private List<City> cities;
    private CityListAdapter adapter;
    private Spinner spinner;

    public CityListView(MainActivity context, List<City> cities) {
        this.context = context;
        this.cities = cities;
    }

    public void init(int resource, String date) {
        updateTime(date);
        initSpinner();
        adapter = new CityListAdapter(context, resource, context.getLayoutInflater());
        ListView cityList = (ListView) context.findViewById(resource);
        cityList.setAdapter(adapter);
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                context.forecastDetail((Integer) view.getTag());
            }
        });
        updateAdapter();
    }

    public void updateTime(String date) {
        TextView textView = (TextView) context.findViewById(R.id.last_time_updated);
        textView.setText("Last time updated: " + date);
    }

    public void initSpinner() {
        spinner = (Spinner) context.findViewById(R.id.order_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.order_by, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    public void updateView(List<City> cities) {
        this.cities = cities;
        updateAdapter();
    }

    private void updateAdapter() {
        adapter.setItens(cities);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        OrderByEnum orderByEnum = OrderByEnum.getOrderByString(((AppCompatTextView) view).getText().toString().toLowerCase(), context);
        if (orderByEnum == null) {
            return;
        }
        switch (orderByEnum) {
            case MAX_TEMP:
                updateView(CityComparator.compareByMaxTemp(cities));
                break;
            case MIN_TEMP:
                updateView(CityComparator.compareByMinTemp(cities));
                break;
            case CITY_NAME:
                updateView(CityComparator.compareByName(cities));
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
