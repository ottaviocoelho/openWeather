package controllers;

import com.example.otavioaugusto.myapplication.ForecastDetailActivity;

import java.util.List;

import models.Forecast;
import repositories.CityRepository;
import repositories.ForecastControllersRepository;
import repositories.ForecastRepository;
import services.ForecastDetailService;
import views.ForecastDetailView;

public class ForecastDetailsController {

    private Long forecastId;
    private ForecastDetailView view;

    private ForecastDetailActivity activity;
    private int resource;

    private ForecastRepository repository = ForecastRepository.getInstance();
    private ForecastDetailService service = ForecastDetailService.getInstance();

    public ForecastDetailsController(ForecastDetailActivity activity, int resource, Long id) {
        this.activity = activity;
        this.resource = resource;
        this.forecastId = id;
        repository.init();
        ForecastControllersRepository.getInstance().add(this);
        List<Forecast> forecasts = repository.getById(forecastId);
        if (forecasts != null) {
            initView(forecasts);
        } else {
            service.query(forecastId);
        }
    }

    public void initView(List<Forecast> forecasts) {
        view = new ForecastDetailView(forecasts, activity);
        view.init(resource, CityRepository.getInstance().getById(forecasts.get(0).getId()).getName());
    }

    public Long getForecastId() {
        return forecastId;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

}
