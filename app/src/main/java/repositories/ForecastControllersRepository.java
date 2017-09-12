package repositories;

import java.util.HashMap;
import java.util.Map;

import controllers.ForecastDetailsController;

public class ForecastControllersRepository {

    private static ForecastControllersRepository repository = new ForecastControllersRepository();

    public static ForecastControllersRepository getInstance() {
        return repository;
    }

    Map<Long, ForecastDetailsController> cache = new HashMap<>();

    public void add(ForecastDetailsController forecast) {
        long k = forecast.getForecastId();
        cache.put(k , forecast);
    }

    public ForecastDetailsController getById(Long id){
        return cache.get(id);
    }

}
