package factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Details;
import models.Forecast;
import models.Main;
import models.Weather;

public abstract class DetailsBuilder {

    public static List<Details> buildDetails(Forecast forecast) {
        ArrayList<Details> details = new ArrayList<>();
        for(int i = 0; i < forecast.getMains().size() ; i++){
            Main main = forecast.getMains().get(i);
            Details detail = new Details();
            Weather weather = forecast.getWeathers().get(forecast.getMains().indexOf(main));
            detail.setDate(getDate(main.getDt()));
            detail.setDescription(weather.getDescription());
            detail.setMain(weather.getMain());
            detail.setHumidity(main.getHumidity());
            detail.setTempMax(main.getTempMax());
            detail.setTempMin(main.getTempMin());
            detail.setCityName(forecast.getName());
            details.add(detail);
        }
        return details;
    }

    private static Date getDate(long seconds){
        long millis = seconds * 1000;
        return new Date(millis);
    }

}
