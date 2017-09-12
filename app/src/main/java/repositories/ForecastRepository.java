package repositories;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controllers.ForecastDetailsController;
import models.Forecast;

public class ForecastRepository {

    private static ForecastRepository repository = new ForecastRepository();

    public static ForecastRepository getInstance() {
        return repository;
    }

    private Map<Long, List<Forecast>> cache = new HashMap<>();

    public void addAll(List<Forecast> forecasts){
        Collections.sort(forecasts);
        long key = forecasts.get(0).getCity().getId();
        if(cache.keySet().contains(key)) {
            cache.remove(key);
        }
        cache.put(key, forecasts);
        ForecastControllersRepository repo = ForecastControllersRepository.getInstance();
        ForecastDetailsController cont = repo.getById(key);
        cont.initView(forecasts);
    }

    public List<Forecast> getById(Long id){
        return cache.get(id);
    }

}
