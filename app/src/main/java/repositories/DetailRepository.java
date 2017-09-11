package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Detail;

public class DetailRepository {

    private Map<Long, List<Detail>> cache = new HashMap<>();

    public void add(Detail detail){
        if(cache.keySet().contains(detail.getCityId())){
            cache.get(detail.getCityId()).add(detail);
        } else {
            List<Detail> details = new ArrayList<>();
            details.add(detail);
            cache.put(detail.getCityId(), details);
        }
    }

    public void addAll(List<Detail> details){
        for (Detail detail : details) {
            add(detail);
        }
    }


    private static DetailRepository instance = new DetailRepository();
    public static DetailRepository getInstance(){
        return instance;
    }

}
