package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.otavioaugusto.myapplication.R;

import java.util.List;

import models.Details;

public class ForecastDetailsAdapter extends ArrayAdapter<Details> {

    private LayoutInflater inflater;
    private List<Details> forecasts;

    public ForecastDetailsAdapter(Context context, int resource, List<Details> forecasts, LayoutInflater inflater) {
        super(context, resource, forecasts);
        this.inflater = inflater;
        this.forecasts = forecasts;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.forecast_list, null, false);
        }
        Details currentForecast = forecasts.get(position);

        TextView tvName = (TextView) convertView.findViewById(R.id.forecast_name);
        TextView tvMaxTemp = (TextView) convertView.findViewById(R.id.max_temp);
        TextView tvMinTemp = (TextView) convertView.findViewById(R.id.min_temp);
        TextView tvHumidity = (TextView) convertView.findViewById(R.id.humidity);
        TextView tvWeather = (TextView) convertView.findViewById(R.id.weather);

        tvName.setText(currentForecast.getDay());
        tvMaxTemp.setText("MAX: " + (int) currentForecast.getTempMax() + " Cº");
        tvMinTemp.setText("MIN: " + (int) currentForecast.getTempMin() + " Cº");
//        tvMaxTemp.setText("MAX: " + Double.toString(currentForecast.getTempMax()) + " Cº");
//        tvMinTemp.setText("MIN: " + Double.toString(currentForecast.getTempMin()) + " Cº");
        tvHumidity.setText("HUMIDITY: " + Integer.toString(currentForecast.getHumidity()) + "%");
        tvWeather.setText("WEATHER: " + currentForecast.getDescription());

        return convertView;
    }

}
