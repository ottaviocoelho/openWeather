package factory;

import java.util.List;

import models.Detail;
import models.Forecast;
import repositories.CityRepository;

public abstract class ForecastBuilder {

    public static Forecast buildForecast(List<Detail> details){
        Forecast forecast = new Forecast();
        Detail firstDetail = details.get(0);
        forecast.setDescription(firstDetail.getDescription());
        forecast.setIcon(firstDetail.getIcon());
        forecast.setTempMax(resolveTempMax(details));
        forecast.setTempMin(resolveTempMin(details));
        forecast.setDt(firstDetail.getDt());
        forecast.setCity(CityRepository.getInstance().getById(firstDetail.getCityId()));
        return forecast;
    }

    private static int resolveTempMax(List<Detail> details) {
        int tempMax = 0;
        for (Detail detail : details) {
            tempMax += (int) detail.getTempMax();
        }
        return tempMax / details.size();
    }

    private static int resolveTempMin(List<Detail> details) {
        int tempMin = 0;
        for (Detail detail : details) {
            tempMin += (int) detail.getTempMin();
        }
        return tempMin / details.size();
    }

}
