package factory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import models.Detail;

public class DetailJsonParser {

    private static  double kelvinConst = 273.15;

    public static List<Detail> parse(JSONObject jsonObject) throws JSONException {
        JSONArray weathers = jsonObject.getJSONArray("list");
        JSONArray mains = jsonObject.getJSONArray("list");
        long cityId = jsonObject.getJSONObject("city").getLong("id");
        List<Detail> details = new ArrayList<>();
        for (int i = 0; i < mains.length(); i++){
            Detail detail = new Detail();
            parseMain(detail, mains.getJSONObject(i));
            parseWeather(detail, weathers.getJSONObject(i));
            detail.setCityId(cityId);
            details.add(detail);
        }
        return details;
    }

    private static void parseWeather(Detail detail, JSONObject jsonObject) throws JSONException {
        JSONArray jsonWeather = jsonObject.getJSONArray("weather");
        JSONObject weatherJsonObject = jsonWeather.getJSONObject(0);
        detail.setDescription(weatherJsonObject.getString("description"));
        detail.setIcon(weatherJsonObject.getString("icon"));
    }

    private static void parseMain(Detail detail, JSONObject jsonObject) throws JSONException {
        JSONObject jsonMain = jsonObject.getJSONObject("main");
        detail.setHumidity(jsonMain.getInt("humidity"));
        detail.setDt(Long.valueOf(jsonObject.getString("dt")) * 1000);
        detail.setTempMin(Double.valueOf(jsonMain.getString("temp_min")) - kelvinConst);
        detail.setTempMax(Double.valueOf(jsonMain.getString("temp_max")) - kelvinConst);
    }

}
