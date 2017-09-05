package factory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import models.City;
import models.Main;
import models.Weather;

public class CityJsonParser {

    private double kelvinConst = 273.15;
    private static CityJsonParser parser = new CityJsonParser();

    public static CityJsonParser getInstance() {
        return parser;
    }

    public City parse(JSONObject jsonObject) throws JSONException {
        Weather weather = parseWeather(jsonObject);
        Main main = parseMain(jsonObject);
        City forecast = new City();
        forecast.setWeather(weather);
        forecast.setMain(main);
        return buildForecast(forecast, jsonObject);
    }

    private City buildForecast(City forecast, JSONObject jsonObject) throws JSONException {
        forecast.setCod(jsonObject.getString("cod"));
        forecast.setId(jsonObject.getLong("id"));
        forecast.setName(jsonObject.getString("name"));
        return parseCoord(forecast, jsonObject);
    }

    private City parseCoord(City forecast, JSONObject jsonObject) throws JSONException {
        JSONObject coordJson = jsonObject.getJSONObject("coord");
        forecast.setCoordLat(Float.valueOf(coordJson.getString("lat")));
        forecast.setCoordLon(Float.valueOf(coordJson.getString("lon")));
        return forecast;
    }

    private Weather parseWeather(JSONObject jsonObject) throws JSONException {
        JSONArray jsonWeather = jsonObject.getJSONArray("weather");
        JSONObject weatherJsonObject = jsonWeather.getJSONObject(0);
        long id = weatherJsonObject.getLong("id");
        String main = weatherJsonObject.getString("main");
        String description = weatherJsonObject.getString("description");
        String icon = weatherJsonObject.getString("icon");
        return new Weather(id, main, description, icon);
    }

    private Main parseMain(JSONObject jsonObject) throws JSONException {
        JSONObject jsonMain = jsonObject.getJSONObject("main");
        double temp = Double.valueOf(jsonMain.getString("temp"));
        int pressure = jsonMain.getInt("pressure");
        int humidity = jsonMain.getInt("humidity");
        Long dt = Long.valueOf(jsonObject.getString("dt"));
        double tempMin = Double.valueOf(jsonMain.getString("temp_min")) - kelvinConst;
        double tempMax = Double.valueOf(jsonMain.getString("temp_max")) - kelvinConst;
        return new Main(temp, pressure, humidity, tempMin, tempMax, dt);
    }

}
