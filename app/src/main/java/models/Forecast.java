package models;

import android.support.annotation.NonNull;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "forecasts")
public class Forecast implements Comparable<Forecast>{

    @DatabaseField(foreign = true, columnName = "city_fk")
    private City city;

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private String description;

    @DatabaseField
    private String icon;

    @DatabaseField
    private double tempMin;

    @DatabaseField
    private double tempMax;

    @DatabaseField
    private long dt;


    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    @Override
    public int compareTo(@NonNull Forecast otherForecast) {
        if (this.getDt() == otherForecast.getDt()){
            return 0;
        }
        return this.getDt() > otherForecast.getDt() ? 1 : -1;
    }
}
