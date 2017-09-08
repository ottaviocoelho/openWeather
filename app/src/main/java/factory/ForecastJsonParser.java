package factory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import models.Forecast;
import models.Main;
import models.Weather;

public class ForecastJsonParser {

    private double kelvinConst = 273.15;

    public Forecast parse(JSONObject jsonObject) throws JSONException {
        List<Weather> weathers = getWeathers(jsonObject.getJSONArray("list"));
        List<Main> mains = getMains(jsonObject.getJSONArray("list"));
        Forecast forecast = new Forecast();
        forecast.setWeather(weathers);
        forecast.setMain(mains);
        return buildForecast(forecast, jsonObject.getJSONObject("city"));
    }

    private List<Main> getMains(JSONArray mainJsonArray) throws JSONException {
        List<Main> mains = new ArrayList<>();
        for (int i = 0; i < mainJsonArray.length(); i++) {
            mains.add(parseMain(mainJsonArray.getJSONObject(i)));
        }
        return mains;
    }

    private List<Weather> getWeathers(JSONArray mainJsonArray) throws JSONException {
        List<Weather> mains = new ArrayList<>();
        for (int i = 0; i < mainJsonArray.length(); i++) {
            mains.add(parseWeather(mainJsonArray.getJSONObject(i)));
        }
        return mains;
    }

    private Forecast buildForecast(Forecast forecast, JSONObject jsonObject) throws JSONException {
        forecast.setId(jsonObject.getLong("id"));
        forecast.setName(jsonObject.getString("name"));
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
        Long dt = Long.valueOf(jsonObject.getString("dt"));
        int pressure = jsonMain.getInt("pressure");
        int humidity = jsonMain.getInt("humidity");
        double tempMin = Double.valueOf(jsonMain.getString("temp_min")) - kelvinConst;
        double tempMax = Double.valueOf(jsonMain.getString("temp_max")) - kelvinConst;
        return new Main(temp, pressure, humidity, tempMin, tempMax, dt);
    }
}
