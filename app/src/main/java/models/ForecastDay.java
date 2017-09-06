package models;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import utils.DayResolver;

public class ForecastDay implements Comparable<ForecastDay>{

    private List<Details> details;
    private int humidity;
    private int tempMax;
    private int tempMin;
    private String day;

    public ForecastDay(List<Details> details){
        this.details = details;
        this.day = resolveDay();
        this.humidity = resolveHumidity();
        this.tempMax = resolveTempMax();
        this.tempMin = resolveTempMin();
    }

    public int getHumidity() {
        return humidity;
    }

    public int getTempMax() {
        return tempMax;
    }

    public int getTempMin() {
        return tempMin;
    }

    public String getDay(){
        return day;
    }

    public String getCityName() {
        return details.get(0).getCityName();
    }

    private String resolveDay() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(details.get(0).getDate());
        day = DayResolver.resolve(calendar.get(Calendar.DAY_OF_WEEK))+ "  " + dateFormat.format(calendar.getTime());
        return day;
    }

    private int resolveHumidity() {
        int humidity = 0;
        for (Details detail : details) {
            humidity += detail.getHumidity();
        }
        return humidity / details.size();
    }

    private int resolveTempMax() {
        int tempMax = 0;
        for (Details detail : details) {
            tempMax += (int) detail.getTempMax();
        }
        return tempMax / details.size();
    }

    private int resolveTempMin() {
        int tempMin = 0;
        for (Details detail : details) {
            tempMin += (int) detail.getTempMin();
        }
        return tempMin / details.size();
    }

    @Override
    public int compareTo(@NonNull ForecastDay otherForecastDay) {
        if (this.details.get(0).getDate().getTime() == otherForecastDay.details.get(0).getDate().getTime()){
            return 0;
        }
        return this.details.get(0).getDate().getTime() > otherForecastDay.details.get(0).getDate().getTime() ? 1 : -1;
    }
}
