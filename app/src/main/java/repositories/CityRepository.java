package repositories;

import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.City;

public class CityRepository {

    private Map<Long, City> cache = new HashMap<>();

    public void add(City city) {
        cache.put(city.getId(), city);
    }

    public List<City> getAll() {
        return new ArrayList<>(cache.values());
    }

    public City getById(long id){
        return cache.get(id);
    }

    public void handleLoad(List<City> cities) {
        for (City city : cities) {
            cache.put(city.getId(), city);
        }
    }

    private static  CityRepository repository = new CityRepository();

    public static CityRepository getInstance() {
        return repository;
    }

}
