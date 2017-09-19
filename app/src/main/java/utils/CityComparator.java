package utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.City;

public class CityComparator{

    public static List<City> compareByName(List<City> cities){
        Collections.sort(cities, new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                return city1.getName().compareTo(city2.getName());
            }
        });
        return cities;
    }

    public static List<City> compareByMaxTemp(List<City> cities){
        Collections.sort(cities, new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                if(city1.getTempMax() == city2.getTempMax()) return 0;
                return city1.getTempMax() > city2.getTempMax() ? 1 : -1;
            }
        });
        return cities;
    }

    public static List<City> compareByMinTemp(List<City> cities){
        Collections.sort(cities, new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                if(city1.getTempMin() == city2.getTempMin()) return 0;
                return city1.getTempMin() > city2.getTempMin() ? 1 : -1;
            }
        });
        return cities;
    }

}
