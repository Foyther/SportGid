/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.service.api.impl;

import android.kfu.entities.KindOfSport;
import android.kfu.entities.Place;
import android.kfu.repository.PlaceRepository;
import android.kfu.service.api.PlaceService;
import android.kfu.service.api.exception.NotFound.PlaceNotFoundException;
import android.kfu.tools.PlaceToPlaceShortResultConverter;

import java.util.Set;

import org.hibernate.InstantiationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public Place getById(Long id) throws PlaceNotFoundException{
        if (id == null) {
            throw new PlaceNotFoundException();
        }
        Place temp = placeRepository.findOne(id);
        if (temp == null) {
            throw new PlaceNotFoundException();
        }
        return temp;
    }

    @Override
    public void save(Place place) throws PlaceNotFoundException {
        if(place != null) {
            placeRepository.save(place);
        } else throw new PlaceNotFoundException();
    }

    @Override
    public Set<Place> getAllByTitle(KindOfSport sport, String city) throws PlaceNotFoundException{
        return placeRepository.findAllBySportAndCity(sport, city);
    }

    @Override
    public void deleteById(Long i) throws PlaceNotFoundException {
        Place place = placeRepository.findById(i);
        if(place != null) {
            placeRepository.delete(i);
        } else throw new PlaceNotFoundException();
    }
}
