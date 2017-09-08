package factory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import models.City;

public class CityJsonParser {

    private double kelvinConst = 273.15;
    private static CityJsonParser parser = new CityJsonParser();

    public static CityJsonParser getInstance() {
        return parser;
    }

    public City parse(JSONObject jsonObject) throws JSONException {
        return buildForecast(new City(), jsonObject);
    }

    private City buildForecast(City city, JSONObject jsonObject) throws JSONException {
        city.setId(jsonObject.getLong("id"));
        city.setName(jsonObject.getString("name"));
        return parseWeather(parseMain(city, jsonObject), jsonObject);
    }

    private City parseWeather(City city, JSONObject jsonObject) throws JSONException {
        JSONArray jsonWeather = jsonObject.getJSONArray("weather");
        JSONObject weatherJsonObject = jsonWeather.getJSONObject(0);
        city.setDescription(weatherJsonObject.getString("description"));
        city.setIcon(weatherJsonObject.getString("icon"));
        return city;
    }

    private City parseMain(City city, JSONObject jsonObject) throws JSONException {
        JSONObject jsonMain = jsonObject.getJSONObject("main");
        city.setTemp(Double.valueOf(jsonMain.getString("temp")) - kelvinConst);
        city.setTempMax(Double.valueOf(jsonMain.getString("temp_max")) - kelvinConst);
        city.setTempMin(Double.valueOf(jsonMain.getString("temp_min")) - kelvinConst);
        return city;
    }

}
