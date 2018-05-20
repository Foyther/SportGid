package android.kfu.repository;

import android.kfu.entities.Map;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by Nurislam on 14.05.2018.
 */
public interface MapRepository extends JpaRepository<Map, Long> {

    Map findFirstByXAndY(Double x, Double y);
    List<Map> findAllById(List<Long> ids);
    List<Map> findByIdIn(List<Long> ids);
}
