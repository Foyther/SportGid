package android.kfu.service.api;

import android.kfu.entities.KindOfSport;
import android.kfu.service.api.exception.NotFound.KindOfSportNotFoundException;

import java.util.List;
import java.util.Set;

/**
 * Created by Nurislam on 17.12.2017.
 */
public interface KindOfSportsService {
    public KindOfSport getById(Long id) throws KindOfSportNotFoundException;
    public List<KindOfSport> getAll() throws KindOfSportNotFoundException;
    public void save(KindOfSport kindOfSport);
    public Set<KindOfSport> getAllByNameIsLike(String name);

}
