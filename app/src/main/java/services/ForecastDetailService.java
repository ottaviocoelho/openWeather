package services;

import org.json.JSONException;
import org.json.JSONObject;

import factory.ForecastJsonParser;
import repositories.ForecastRepository;
import tasks.ForecastDetailTask;

public class ForecastDetailService {

    private static final ForecastDetailService service = new ForecastDetailService();
    private final ForecastJsonParser parser = new ForecastJsonParser();

    public static ForecastDetailService getInstance() {
        return service;
    }

    public void query(Long id) {
        String urlIdBase = "http://api.openweathermap.org/data/2.5/forecast?id=";
        String apiKey = "&APPID=06f3e9ccfa90c34fe75c672900b2b8ff";
        String url = urlIdBase + id + apiKey;
        executeTask(url);
    }

    public void addToRepository(JSONObject jsonObject) {
        try {
            ForecastRepository.getInstance().add(parser.parse(jsonObject));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void executeTask(String url) {
        final ForecastDetailTask task = new ForecastDetailTask();
        try {
            task.execute(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
