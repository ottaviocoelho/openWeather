package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.otavioaugusto.myapplication.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.Forecast;
import utils.DayResolver;

public class ForecastDetailsAdapter extends ArrayAdapter<Forecast> {

    private LayoutInflater inflater;
    private List<Forecast> forecasts;

    public void setItens(List<Forecast> forecasts) {
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
        Forecast currentForecast = forecasts.get(position);

        TextView tvName = (TextView) convertView.findViewById(R.id.forecast_name);
        TextView tvMaxTemp = (TextView) convertView.findViewById(R.id.max_temp);
        TextView tvMinTemp = (TextView) convertView.findViewById(R.id.min_temp);

        tvName.setText(resolveDay(currentForecast));
        tvMaxTemp.setText("MAX: " + currentForecast.getTempMax() + " Cº");
        tvMinTemp.setText("MIN: " + currentForecast.getTempMin() + " Cº");

        return convertView;
    }

    public int getCount(){
        if(forecasts != null){
            return forecasts.size();
        }
        return 0;
    }

    private String resolveDay(Forecast forecast) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(forecast.getDt()));
        return DayResolver.resolve(calendar.get(Calendar.DAY_OF_WEEK))+ "  " + dateFormat.format(calendar.getTime());
    }

}
