package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.City;

public class CityRepository {

    private Map<Long, City> cache = new HashMap<>();

    public void add(City city) {
        if(cache.size() < 20) {
            cache.put(city.getId(), city);
        }
    }

    public void remove(Long id){
        cache.remove(id);
    }

    public List<City> getAll() {
        return new ArrayList<>(cache.values());
    }

    public City getById(long id){
        return cache.get(id);
    }

    public void handleLoad(List<City> cities) {
        if(cities.size() > 20) cities = cities.subList(0, 19);
        for (City city : cities) {
            cache.put(city.getId(), city);
        }
    }

    private static  CityRepository repository = new CityRepository();

    public static CityRepository getInstance() {
        return repository;
    }

}
