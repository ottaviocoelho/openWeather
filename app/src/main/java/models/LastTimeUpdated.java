package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "last_time_updated_t")
public class LastTimeUpdated {

    @DatabaseField
    private long cityId;

    @DatabaseField
    private long dt;

    @DatabaseField
    private int id;

    public LastTimeUpdated() {
        super();
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
