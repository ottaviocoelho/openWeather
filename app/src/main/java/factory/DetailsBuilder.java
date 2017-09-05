package factory;

import android.content.Context;

import com.example.otavioaugusto.myapplication.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.Details;
import models.Forecast;
import models.Main;
import models.Weather;

public abstract class DetailsBuilder {

    public static List<Details> buildDetails(Context context, Forecast forecast) {
        ArrayList<Details> details = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM");
        Date date = new Date();
        String dateFormated = dateFormat.format(date);
        for(int i = 0; i < 8 ; i++){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, i);
            Main main = forecast.getMain().get(i);
            Details detail = new Details();
            Weather weather = forecast.getWeather().get(forecast.getMain().indexOf(main));
            String data = String.format(context.getString(R.string.day), String.valueOf(forecast.getMain().indexOf(main) + 1), dateFormat.format(calendar.getTime()));
            detail.setDay(calendar.get(Calendar.DAY_OF_WEEK));
            detail.setDescription(weather.getDescription());
            detail.setMain(weather.getMain());
            detail.setHumidity(main.getHumidity());
            detail.setTempMax(main.getTempMax());
            detail.setTempMin(main.getTempMin());
            details.add(detail);
        }
        return details;
    }

}
