package repositories;

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

    public City searchByFullName(String cityName, ArrayList<City> cities) {
        for (City city : cities) {
            if(cityName.equals(city.getName())) return city;
        }
        return null;
    }

    public City searchByName(String cityName, ArrayList<City> cities) {
        for (City city : cities) {
            if(city.getName().contains(cityName)) return city;
        }
        return null;
    }

    public City getById(long id){
        return cache.get(id);
    }

    private static  CityRepository repository = new CityRepository();

    public static CityRepository getInstance() {
        return repository;
    }

}
