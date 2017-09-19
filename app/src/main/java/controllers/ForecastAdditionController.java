package controllers;

import android.widget.EditText;

import com.example.otavioaugusto.myapplication.AddForecastActivity;
import com.example.otavioaugusto.myapplication.R;

import services.CityService;
import utils.RequestListener;

public class ForecastAdditionController {

    private CityService service;
    private AddForecastActivity addForecastActivity;
    public void init(AddForecastActivity addForecastActivity) {
        this.addForecastActivity = addForecastActivity;
        service = CityService.getInstance();
    }

    public void addForecast(RequestListener listener) {
        String url = extractName();
        service.queryByName(url, listener);
    }

    private String extractName() {
        String cityName= getString(R.id.city_name_edit);
        String countryCode = getString(R.id.country_code_edit);

        if(isValid(cityName)) {
            if(isValid(countryCode) && countryCode.length() == 2) {
                return cityName + "," + countryCode;
            }
            return  cityName;
        }
        return  "";
    }

    private String getString(int resource) {
        EditText editText = (EditText) addForecastActivity.findViewById(resource);
        return  editText.getText().toString();
    }

    private boolean isValid(String name) {
        boolean notEmpty = !name.trim().equals("");
        boolean notNull = name != null;
        return notEmpty && notNull;
    }

}
