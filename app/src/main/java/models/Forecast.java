package models;

import java.util.List;

public class Forecast {

    private List<Weather> weather;
    private List<Main> main;
    private Long id;
    private String name;

    private Float coordLon;
    private Float coordLat;

    public List<Main> getMain() {
        return main;
    }

    public void setMain(List<Main> main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCoordLon() {
        return coordLon;
    }

    public void setCoordLon(Float coordLon) {
        this.coordLon = coordLon;
    }

    public Float getCoodLat() {
        return coordLat;
    }

    public void setCoordLat(Float coordLat) {
        this.coordLat = coordLat;
    }
}
