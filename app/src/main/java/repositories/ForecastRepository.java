package repositories;

import java.util.HashMap;
import java.util.Map;

import models.Forecast;

public class ForecastRepository {

    private static ForecastRepository repository = new ForecastRepository();

    public static ForecastRepository getInstance() {
        return repository;
    }

    private Map<Long, Forecast> cache;

    public void init() {
        cache = new HashMap<>();
    }

    public void add(Forecast forecast) {
        cache.put(forecast.getId(), forecast);
        ForecastControllersRepository.getInstance() .getById(forecast.getId()).addForecast(forecast);
    }

    public Forecast getById(Long id){
        return cache.get(id);
    }

    public boolean cacheContains(Long id) {
        return cache.containsKey(id);
    }
}
