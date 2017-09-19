package controllers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.otavioaugusto.myapplication.MainActivity;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DatabaseHelper;
import models.City;
import models.Detail;
import models.Forecast;
import repositories.CityRepository;
import repositories.DetailRepository;
import repositories.ForecastRepository;
import services.CityService;
import views.CityListView;

public class CityListController {

    private final String pref_key = "all_cities";
    private CityListView view;
    private CityRepository repository = CityRepository.getInstance();
    private CityService service = CityService.getInstance();
    private DatabaseHelper helper;
    private MainActivity activity;

    public void init(final MainActivity mainActivity, int resource) throws SQLException {
        activity = mainActivity;
        helper = OpenHelperManager.getHelper(mainActivity, DatabaseHelper.class);
        Dao<City, Long> dao = helper.getCityDAO();
        List<City> cities = dao.queryForAll();
        repository.handleLoad(cities);
        view = new CityListView(mainActivity, repository.getAll());
        view.init(resource, getLastUpdated());
        service.init();
        service.updateAll(repository.getAll());
    }

    public void addCity(City city){
        try {
            helper.getCityDAO().createOrUpdate(city);
            repository.add(city);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateTime();
        view.updateView(repository.getAll());
        view.updateTime(getLastUpdated());
    }

    private void updateTime() {
        Date date = new Date();
        SharedPreferences sharedPreferences = activity.getPreferences(activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(pref_key, date.getTime());
        editor.commit();
    }

    public String getLastUpdated() {
        SharedPreferences sharedPreferences = activity.getPreferences(activity.MODE_PRIVATE);
        long time = sharedPreferences.getLong(pref_key, 0L);
        if(time == 0L) {
            return "";
        }
        return new SimpleDateFormat("dd/MM h:mm").format(time);
    }

    public void removeCity(Integer id) {
        try {
            Dao<Forecast, Long> forecastDAO = helper.getForecastDAO();
            Dao<Detail, Void> detailDAO = helper.getDetailDAO();
            List<Forecast> forecasts = forecastDAO.queryForEq("city_fk", id);
            List<Detail> details = new ArrayList<>();
            for (Forecast forecast : forecasts) {
                details.addAll(detailDAO.queryForEq("forecast_fk", forecast.getId()));
            }
            detailDAO.delete(details);
            forecastDAO.delete(forecasts);
            helper.getCityDAO().delete(repository.getById(id));
            repository.remove(Long.valueOf(id));
            ForecastRepository.getInstance().removeById(Long.valueOf(id));
            DetailRepository.getInstance().removeById(Long.valueOf(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.updateView(repository.getAll());
    }

    public Context getContext() {
        return activity;
    }

    public void openDetail(int id) {
        activity.forecastDetail(id);
    }

    private static CityListController controller = new CityListController();

    public static CityListController getInstance() {
        return controller;
    }

    public void closeHelper() {
        helper.close();
    }

}
