package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.otavioaugusto.myapplication.R;

import java.util.List;

import models.ForecastDay;

public class ForecastDetailsAdapter extends ArrayAdapter<ForecastDay> {

    private LayoutInflater inflater;
    private List<ForecastDay> forecasts;

    public void setItens(List<ForecastDay> forecasts) {
        this.forecasts = forecasts;
        notifyDataSetChanged();
    }

    public ForecastDetailsAdapter(Context context, int resource, LayoutInflater inflater) {
        super(context, resource);
        this.inflater = inflater;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.forecast_list, null, false);
        }
        ForecastDay currentForecast = forecasts.get(position);

        TextView tvName = (TextView) convertView.findViewById(R.id.forecast_name);
        TextView tvMaxTemp = (TextView) convertView.findViewById(R.id.max_temp);
        TextView tvMinTemp = (TextView) convertView.findViewById(R.id.min_temp);
        TextView tvHumidity = (TextView) convertView.findViewById(R.id.humidity);

        tvName.setText(currentForecast.getDay());
        tvMaxTemp.setText("MAX: " + currentForecast.getTempMax() + " Cº");
        tvMinTemp.setText("MIN: " + currentForecast.getTempMin() + " Cº");
        tvHumidity.setText("HUMIDITY: " + Integer.toString(currentForecast.getHumidity()) + "%");

        return convertView;
    }

    public int getCount(){
        if(forecasts != null){
            return forecasts.size();
        }
        return 0;
    }

}
