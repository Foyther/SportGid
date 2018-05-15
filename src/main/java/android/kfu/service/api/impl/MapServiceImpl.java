package android.kfu.service.api.impl;

import android.kfu.entities.Map;
import android.kfu.repository.MapRepository;
import android.kfu.service.api.MapService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nurislam on 14.05.2018.
 */
@Service
public class MapServiceImpl implements MapService {
    @Autowired
    private MapRepository repository;

    @Override
    public void save(Map map) {
        if(map != null) {
            repository.save(map);
        }
    }

    @Override
    public Map getByXAndY(Double x, Double y) {
        return repository.findFirstByXAndY(x, y);
    }

    @Override
    public Map getById(Long id) {
        return repository.findOne(id);
    }
}
