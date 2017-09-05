package tasks;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import services.ForecastDetailService;

public class ForecastDetailTask extends AsyncTask<String, Void, List<JSONObject>> {

    @Override
    protected List<JSONObject> doInBackground(String... urls) {
        final OkHttpClient client = new OkHttpClient();
        List<JSONObject> jsonObjects = new ArrayList<>();
        for(String url : urls) {
            try {
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = client.newCall(request).execute();
                ResponseBody responseBody = response.body();
                if(responseBody != null) {
                    jsonObjects.add(new JSONObject(responseBody.string()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonObjects;
    }

    @Override
    protected void onPostExecute(List<JSONObject> jsonObjects) {
        final ForecastDetailService service = ForecastDetailService.getInstance();
        for(JSONObject jsonObject : jsonObjects) {
            service.addToRepository(jsonObject);
        }
    }

}
