package controllers;

import com.example.otavioaugusto.myapplication.ForecastDetailActivity;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import db.DatabaseHelper;
import models.Detail;
import models.Forecast;
import repositories.CityRepository;
import repositories.DetailRepository;
import repositories.ForecastControllersRepository;
import repositories.ForecastRepository;
import services.ForecastDetailService;
import views.ForecastDetailView;

import static com.j256.ormlite.android.apptools.OpenHelperManager.getHelper;

public class ForecastDetailsController {

    private Long forecastId;

    private String cityName;

    private ForecastDetailActivity activity;
    private int resource;

    private ForecastRepository forecastRepository= ForecastRepository.getInstance();
    private DetailRepository detailRepository = DetailRepository.getInstance();
    private ForecastDetailService service = ForecastDetailService.getInstance();

    public ForecastDetailsController(ForecastDetailActivity activity, int resource, Long id) throws java.sql.SQLException {
        this.activity = activity;
        this.resource = resource;
        this.forecastId = id;
        this.cityName = CityRepository.getInstance().getById(forecastId).getName();
        init();
    }

    private void init() throws java.sql.SQLException {
        ForecastControllersRepository.getInstance().add(this);
        List<Forecast> forecasts = getHelper(activity, DatabaseHelper.class).getForecastDAO().queryForEq("city_fk", forecastId);
        if (forecasts.size() > 0) forecastRepository.addAll(forecasts);
        if (forecasts != null) {
            initView(forecasts);
        }
        service.query(forecastId);
    }

    public void initView(List<Forecast> forecasts) {
        new ForecastDetailView(forecasts, activity).init(resource, cityName);
    }

    public void updateModels() {
        initView(forecastRepository.getById(forecastId));
        try {
            createOrUpdateForecast(forecastRepository.getById(forecastId));
            createOrUpdateDetail(detailRepository.getById(forecastId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createOrUpdateForecast(List<Forecast> forecasts) throws SQLException {
        Dao<Forecast, Long> forecastLongDao = OpenHelperManager.getHelper(activity, DatabaseHelper.class).getForecastDAO();
        forecastLongDao.delete(getAllForecastFromCityId());
        for (Forecast forecast : forecasts) {
            forecastLongDao.create(forecast);
        }
    }

    private void createOrUpdateDetail(List<Detail> details) throws SQLException {
        Dao<Detail, Void> detailVoidDao = OpenHelperManager.getHelper(activity, DatabaseHelper.class).getDetailDAO();
        detailVoidDao.delete(getAllDetailFromCityId());
        for (Detail detail : details) {
            detailVoidDao.create(detail);
        }
    }

    public Long getForecastId() {
        return forecastId;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    private List<Forecast> getAllForecastFromCityId() throws SQLException {
        return getHelper(activity, DatabaseHelper.class).getForecastDAO().queryForEq("city_fk", forecastId);
    }

    private List<Detail> getAllDetailFromCityId() throws SQLException {
        return getHelper(activity, DatabaseHelper.class).getDetailDAO().queryForEq("forecast_fk", forecastId);
    }

}
