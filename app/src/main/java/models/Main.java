package models;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mains")
public class Main{

    @DatabaseField(columnName = "forecast_id", foreign = true)
    private Forecast forecastId;

    @DatabaseField
    private double temp;

    @DatabaseField
    private long dt;

    @DatabaseField
    private int pressure;

    @DatabaseField
    private int humidity;

    @DatabaseField
    private double tempMin;

    @DatabaseField
    private double tempMax;

    public Main(double temp, int pressure, int humidity, double tempMin, double tempMax, long dt, Forecast forecastId) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.dt = dt;
        this.forecastId = forecastId;
    }

    public Forecast getForecastId() {
        return forecastId;
    }

    public void setForecastId(Forecast forecastId) {
        this.forecastId = forecastId;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
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

}
