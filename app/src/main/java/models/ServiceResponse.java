package models;

import org.json.JSONObject;

import java.util.List;

import okhttp3.Response;

public class ServiceResponse {

    private Response response;
    private List<JSONObject> jSonObjects;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<JSONObject> getjSonObjects() {
        return jSonObjects;
    }

    public void setjSonObjects(List<JSONObject> jSonObjects) {
        this.jSonObjects = jSonObjects;
    }
}
