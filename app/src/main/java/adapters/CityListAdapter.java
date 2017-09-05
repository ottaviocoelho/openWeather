package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.otavioaugusto.myapplication.R;

import java.util.List;

import models.City;

public class CityListAdapter extends ArrayAdapter<City> {

    private LayoutInflater inflater;
    private List<City> cities;

    public CityListAdapter(Context context, int resource, List<City> cities, LayoutInflater inflater) {
        super(context, resource, cities);
        this.inflater = inflater;
        this.cities = cities;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.city_list, null, false);
        }
        City currentCity = cities.get(position);

        TextView tvName = (TextView) convertView.findViewById(R.id.city_name);
        TextView tvMaxTemp = (TextView) convertView.findViewById(R.id.max_temp);
        TextView tvMinTemp = (TextView) convertView.findViewById(R.id.min_temp);

        tvName.setText(currentCity.getName());
        tvMaxTemp.setText("MAX: " + (int) currentCity.getMain().getTempMax() + " Cº");
        tvMinTemp.setText("MIN: " + (int) currentCity.getMain().getTempMin() + " Cº");
//        String.format("%.2f", currentCity.getMain().getTempMin());
//        tvMaxTemp.setText("MAX: " + Double.toString(currentCity.getMain().getTempMax()) + " Cº");
//        tvMinTemp.setText("MIN: " + Double.toString(currentCity.getMain().getTempMin()) + " Cº");

        convertView.setId(currentCity.getId().intValue());

        return convertView;
    }

}