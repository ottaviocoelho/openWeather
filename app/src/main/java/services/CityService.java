package services;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import controllers.CityListController;
import factory.CityJsonParser;
import models.Forecast;
import tasks.CityTask;

public class CityService {

    private static final CityService service = new CityService();
    private CityListController controller;
    private CityJsonParser parser;

    final private String apiKey = "&APPID=06f3e9ccfa90c34fe75c672900b2b8ff";

    public static CityService getInstance() {
        return service;
    }

    public void init() {
        controller = CityListController.getInstance();
        parser = CityJsonParser.getInstance();
    }

    public void updateAll(List<Forecast> forecasts) {
        String urlIdBase = "http://api.openweathermap.org/data/2.5/weather?id=";
        String url = urlIdBase + getMultipleCitiesString(forecasts) + apiKey;
        executeTask(url);
    }

    public void queryByName(String name){
        String urlNameBase = "http://api.openweathermap.org/data/2.5/weather?q=";
        String url = urlNameBase + name + apiKey;
        executeTask(url);
    }

    public void queryById(Long id) {
        String urlIdBase = "http://api.openweathermap.org/data/2.5/weather?id=";
        String url = urlIdBase + id + apiKey;
    }

    public void addToRepository(JSONObject jsonObject) {
        try {
            controller.addCity(parser.parse(jsonObject));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getMultipleCitiesString(List<Forecast> forecasts) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < forecasts.size() && i < 20; i++) {
            builder.append(forecasts.get(i).getId());
            if(i < forecasts.size() -1 && i < 19){
                builder.append(",");
            }
        }
        return builder.toString();
    }

    private void executeTask(String url) {
        final CityTask task = new CityTask();
        try {
            task.execute(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
