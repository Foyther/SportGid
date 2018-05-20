package android.kfu.service.api;

import android.kfu.entities.Map;
import javassist.NotFoundException;

import java.util.List;

/**
 * Created by Nurislam on 14.05.2018.
 */
public interface MapService {

    void save(Map map);
    Map getByXAndY(Double x, Double y);
    Map getById(Long id);
    List<Map> getByListId(List<Long> mapLongs) throws NotFoundException;
}
