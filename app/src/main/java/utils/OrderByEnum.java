package utils;

import android.content.Context;

import com.example.otavioaugusto.myapplication.R;

/**
 * Created by otavio.augusto on 14/09/2017.
 */

public enum OrderByEnum {

    MAX_TEMP(R.string.order_by_max_temp),
    MIN_TEMP(R.string.order_by_min_temp),
    CITY_NAME(R.string.order_by_city_name);

    public int id;

    OrderByEnum(int id) {
        this.id = id;
    }

    public static OrderByEnum getOrderByString(String value, Context context){
        for (OrderByEnum orderByEnum : OrderByEnum.values()) {
            if(context.getString(orderByEnum.id).equals(value)){
                return orderByEnum;
            }
        }

        return null;
    }


}
