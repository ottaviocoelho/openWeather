package factory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Detail;
import models.Forecast;

public abstract class ForecastFactory {

    public static Map<Integer, Forecast> getForecasts(List<Detail> details){
        Map<Integer, List<Detail>> detailsByDay = new HashMap<>();
        Map<Integer, Forecast> forecasts = new HashMap<>();
        for (Detail detail : details) {
            Integer dayOfMonth = getDayOfMonth(detail.getDt());
            if(detailsByDay.get(dayOfMonth) != null){
                detailsByDay.get(dayOfMonth).add(detail);
            } else {
                List<Detail> list = new ArrayList<>();
                list.add(detail);
                detailsByDay.put(dayOfMonth, list);
            }
        }
        for (Integer key : detailsByDay.keySet()) {
            Forecast forecast = ForecastBuilder.buildForecast(detailsByDay.get(key));
            forecasts.put(key, forecast);
        }
        return forecasts;
    }

    private static int getDayOfMonth(long dt) {
        Date date = new Date(dt);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

}
