package repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Forecast;

public class ForecastRepository {

    private static ForecastRepository repository = new ForecastRepository();

    public static ForecastRepository getInstance() {
        return repository;
    }

    private Map<Long, List<Forecast>> cache;

    public void init() {
        cache = new HashMap<>();
    }

    public void add(Forecast forecast) {
        if(cache.keySet().contains(forecast.getId())){
            cache.get(forecast.getId()).add(forecast);
        } else {
            List<Forecast> forecasts = new ArrayList<>();
            forecasts.add(forecast);
            cache.put(forecast.getId(), forecasts);
        }
    }

    public void addAll(List<Forecast> forecasts){
        Collections.sort(forecasts);
        for (Forecast forecast : forecasts) {
            add(forecast);
        }
        ForecastControllersRepository.getInstance().getById(forecasts.get(0).getId()).initView(forecasts);
    }

    public List<Forecast> getById(Long id){
        return cache.get(id);
    }

}
