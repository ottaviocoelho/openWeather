package controllers;

import com.example.otavioaugusto.myapplication.ForecastDetailActivity;

import models.Forecast;
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
        Forecast forecast = repository.getById(forecastId);
        if (forecast != null) {
            initView(forecast);
        } else {
            service.query(forecastId);
        }
    }

    private void initView(Forecast forecast) {
        view = new ForecastDetailView(forecast, activity);
        view.init(resource);
    }

    public void addForecast(Forecast forecast) {
        initView(forecast);
    }

    public Long getForecastId() {
        return forecastId;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

}
