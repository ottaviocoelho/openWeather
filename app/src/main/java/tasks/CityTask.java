package tasks;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import models.ServiceResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import services.CityService;

public class CityTask extends AsyncTask<String, Void, ServiceResponse> {

    @Override
    protected ServiceResponse doInBackground(String... urls) {
        final OkHttpClient client = new OkHttpClient();
        ServiceResponse serviceResponse = new ServiceResponse();
        for(String url : urls) {
            try {
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = client.newCall(request).execute();
                ResponseBody responseBody = response.body();
                serviceResponse.setResponse(response);
                List<JSONObject> jsonObjects = new ArrayList<>();
                if(responseBody != null) {
                    jsonObjects.add(new JSONObject(responseBody.string()));
                }
                serviceResponse.setjSonObjects(jsonObjects);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return serviceResponse;
    }

    @Override
    protected void onPostExecute(ServiceResponse serviceResponse) {
        final CityService service = CityService.getInstance();
        if(serviceResponse.getResponse().code() == 200) {
            List<JSONObject> jsonObjects = serviceResponse.getjSonObjects();
            for(JSONObject jsonObject : jsonObjects) {
                service.addToRepository(jsonObject);
            }
        } else {
            service.notifyError();
        }
    }

}
