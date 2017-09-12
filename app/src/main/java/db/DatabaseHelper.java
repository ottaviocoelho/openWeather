package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import models.City;
import models.Detail;
import models.Forecast;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "openWeather.db";

    private static final int DATABASE_VERSION = 4;

    private Dao<City, Long> cityDAO = null;
    private Dao<Detail, Void> detailDAO = null;
    private Dao<Forecast, Long> forecastDAO = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, City.class);
            TableUtils.createTable(connectionSource, Forecast.class);
            TableUtils.createTable(connectionSource, Detail.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Detail.class, true);
            TableUtils.dropTable(connectionSource, Forecast.class, true);
            TableUtils.dropTable(connectionSource, City.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<City, Long> getCityDAO() throws SQLException {
        if(cityDAO == null){
            cityDAO = getDao(City.class);
        }
        return cityDAO;
    }

    public Dao<Detail, Void> getDetailDAO() throws SQLException {
        if(detailDAO == null){
            detailDAO = getDao(Detail.class);
        }
        return detailDAO;
    }

    public Dao<Forecast, Long> getForecastDAO() throws SQLException {
        if(forecastDAO == null){
            forecastDAO = getDao(Forecast.class);
        }
        return forecastDAO;
    }

    @Override
    public void close() {
        super.close();
        cityDAO = null;
        forecastDAO = null;
        detailDAO = null;
    }


}
