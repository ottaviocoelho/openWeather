package models;

public class City {

    private Weather weather;
    private Main main;
    private Long id;
    private String name;
    private String cod;

    private Float coordLon;
    private Float coordLat;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
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

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
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
