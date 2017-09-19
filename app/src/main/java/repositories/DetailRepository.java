package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Detail;

public class DetailRepository {

    private Map<Long, List<Detail>> cache = new HashMap<>();

    public void addDetails(List<Detail> details){
        long key = details.get(0).getCityId();
        if(cache.keySet().contains(key)) {
            cache.remove(key);
        }
        cache.put(key, details);
    }

    public void removeById(Long id){
        cache.remove(id);
    }

    public List<Detail> getById(long id) {
        return new ArrayList<>(cache.get(id));
    }

    public boolean containDetails(long id) {
        return cache.keySet().contains(id);
    }

    private static DetailRepository instance = new DetailRepository();
    public static DetailRepository getInstance(){
        return instance;
    }

}
