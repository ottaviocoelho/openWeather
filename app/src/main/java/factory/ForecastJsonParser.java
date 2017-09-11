package factory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Detail;
import models.Forecast;
import repositories.DetailRepository;
import repositories.ForecastRepository;

public class ForecastJsonParser {

    public void parseForecast(JSONObject jsonObject) {
        try {
            List<Detail> details = DetailJsonParser.parse(jsonObject);
            Map<Integer, Forecast> forecasts = ForecastFactory.getForecasts(details);
            addForecasts(forecasts, details);
            DetailRepository.getInstance().addAll(details);
            ForecastRepository.getInstance().addAll(new ArrayList<>(forecasts.values()));
            } catch(JSONException e){
                e.printStackTrace();
            }
    }

    private void addForecasts(Map<Integer, Forecast> forecasts, List<Detail> details){
        for (Detail detail : details){
            detail.setForecast(forecasts.get(getDayOfMonth(detail.getDt())));
        }
    }

    private static int getDayOfMonth(long dt) {
        Date date = new Date(dt);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
