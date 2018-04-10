package android.kfu.service.api.impl;

import android.kfu.entities.KindOfSport;
import android.kfu.repository.KindOfSportRepository;
import android.kfu.service.api.KindOfSportsService;
import android.kfu.service.api.exception.NotFound.KindOfSportNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Nurislam on 17.12.2017.
 */
@Service
public class KindOfSportsServiceImpl implements KindOfSportsService {
    @Autowired
    private KindOfSportRepository kindOfSportRepository;

    @Override
    public KindOfSport getById(Long id) throws KindOfSportNotFoundException{
        if (id == null) {
            return null;
        }
        KindOfSport temp = kindOfSportRepository.findOne(id);
        if (temp != null) {
            return temp;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<KindOfSport> getAll() throws KindOfSportNotFoundException{
        ArrayList<KindOfSport> list = (ArrayList<KindOfSport>) kindOfSportRepository.findAll();
        return list;
    }

    public void save(KindOfSport kindOfSport){
        kindOfSportRepository.save(kindOfSport);
    }

    @Override
    public Set<KindOfSport> getAllByNameIsLike(String name) {
        return kindOfSportRepository.findAllByNameStartingWith(name);
    }

}
