package factory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Details;
import models.ForecastDay;

public class ForecastDayBuilder {

    public static List<ForecastDay> build(List<Details> details){
        Map<Integer, List<Details>> detailsByDay = new HashMap<>();
        List<ForecastDay> forecastDays = new ArrayList<>();
        for (Details detail : details) {
            Integer dayOfMonth = getDayOfMonth(detail.getDate());
            if(detailsByDay.get(dayOfMonth) != null){
                detailsByDay.get(dayOfMonth).add(detail);
            } else {
                List<Details> list = new ArrayList<>();
                list.add(detail);
                detailsByDay.put(dayOfMonth, list);
            }
        }
        for (Integer key : detailsByDay.keySet()) {
            forecastDays.add(new ForecastDay((detailsByDay.get(key))));
        }
        Collections.sort(forecastDays);
        return forecastDays;
    }

    private static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}


