package models;

import java.util.List;

public class Forecast {

    private List<Weather> weathers;
    private List<Main> mains;
    private Long id;
    private String name;

    public List<Main> getMains() {
        return mains;
    }

    public void setMains(List<Main> main) {
        this.mains = main;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
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
}
