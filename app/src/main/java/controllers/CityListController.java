package controllers;

import com.example.otavioaugusto.myapplication.MainActivity;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import db.DatabaseHelper;
import models.City;
import repositories.CityRepository;
import services.CityService;
import views.CityListView;

public class CityListController {

    private CityListView view;
    private CityRepository repository = CityRepository.getInstance();
    private CityService service = CityService.getInstance();
    private DatabaseHelper helper;

    public void init(final MainActivity mainActivity, int resource) throws SQLException {
        helper = OpenHelperManager.getHelper(mainActivity, DatabaseHelper.class);
        Dao<City, Long> dao = helper.getCityDAO();
        List<City> cities = dao.queryForAll();
        repository.handleLoad(cities);
        view = new CityListView(mainActivity, repository.getAll());
        view.init(resource);
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
        view.updateView(repository.getAll());
    }

    private static CityListController controller = new CityListController();

    public static CityListController getInstance() {
        return controller;
    }

    public void closeHelper() {
        helper.close();
    }

}
