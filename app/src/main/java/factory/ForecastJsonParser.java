package factory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Detail;
import models.Forecast;
import repositories.DetailRepository;
import repositories.ForecastControllersRepository;
import repositories.ForecastRepository;

public class ForecastJsonParser {

    public void parseForecast(List<Detail> details) {
        Map<Integer, Forecast> forecasts = ForecastFactory.getForecasts(details);
        addForecasts(forecasts, details);
        DetailRepository.getInstance().addDetails(details);
        ForecastRepository.getInstance().addAll(new ArrayList<>(forecasts.values()));
        ForecastControllersRepository.getInstance().getById(details.get(0).getCityId()).updateModels();
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

    private static final ForecastJsonParser instance = new ForecastJsonParser();
    public static ForecastJsonParser getInstance() { return instance; }
}
