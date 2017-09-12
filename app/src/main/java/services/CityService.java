package services;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import controllers.CityListController;
import factory.CityJsonParser;
import models.City;
import tasks.CityTask;

public class CityService {

    private static final CityService service = new CityService();
    private CityListController controller;
    private CityJsonParser parser;
    private Date lastTimeQueried;

    final private String apiKey = "&APPID=06f3e9ccfa90c34fe75c672900b2b8ff";

    public static CityService getInstance() {
        return service;
    }

    public void init() {
        lastTimeQueried = new Date();
        controller = CityListController.getInstance();
        parser = CityJsonParser.getInstance();
    }

    public void updateAll(List<City> cities) {
        for (City city : cities) {
            queryById(city.getId());
        }
    }

    public void queryByName(String name){
        String urlNameBase = "http://api.openweathermap.org/data/2.5/weather?q=";
        String url = urlNameBase + name + apiKey;
        executeTask(url);
    }

    public void queryById(Long id) {
        if(calculateLasTime()) {
            String urlIdBase = "http://api.openweathermap.org/data/2.5/weather?id=";
            String url = urlIdBase + id + apiKey;
            executeTask(url);
        }
    }

    public void addToRepository(JSONObject jsonObject) {
        try {
            controller.addCity(parser.parse(jsonObject));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void notifyError() {
        //todo
    }

    private void executeTask(String url) {
        final CityTask task = new CityTask();
        try {
            task.execute(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean calculateLasTime() {
        Date newDate = new Date();
        long diff = newDate.getTime() - lastTimeQueried.getTime();
        if(((diff / (60 * 1000)) % 60) > 3) {
            lastTimeQueried = newDate;
            return true;
        }
        return false;
    }

}
