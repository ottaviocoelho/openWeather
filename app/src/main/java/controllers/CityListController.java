package controllers;

import com.example.otavioaugusto.myapplication.MainActivity;

import models.City;
import repositories.CityRepository;
import services.CityService;
import views.CityListView;

public class CityListController {

    private CityListView view;
    private CityRepository repository = CityRepository.getInstance();
    private CityService service = CityService.getInstance();

    public void init(final MainActivity mainActivity, int resource) {
        view = new CityListView(mainActivity, repository.getAll());
        view.init(resource);
        service.init();
    }

    public void addCity(City city) {
        repository.add(city);
        view.updateView(repository.getAll());
    }

    private static CityListController controller = new CityListController();

    public static CityListController getInstance() {
        return controller;
    }

    public void addByName(String name) {
        service.queryByName(name);
    }

}
