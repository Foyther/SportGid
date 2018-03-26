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

@Service
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
    public void deleteById(int i) throws PlaceNotFoundException {
        placeRepository.deleteById(i);
    }

    //    @Override
//    public PlacesResult getShortInfoPlacesByLocation(String city, User userWhoBrowses) throws IllegalArgumentException{
//
//        if (city == null) {
//            throw new IllegalArgumentException("Country and city must be not null." +
//                    "Сity = \"" + city + "\"");
//        }
//
//        Set<Place> places = placeRepository.findPlacesByCity(city);
//
//        Set<PlaceShortResult> placeShortResults = new HashSet<>();
//
//        PlaceShortResult placeShortResult;
//
//        if (places != null) {
//            for (Place place : places) {
//                if (place != null)  {
//                        placeShortResult = placeShortResultConverter.convert(userWhoBrowses, place);
//                        if (placeShortResult != null) {
//                            placeShortResults.add(placeShortResult);
//                        }
//
//                }
//            }
//        }
//
//        PlacesResult placesResult = new PlacesResult();
//        placesResult.setPlaces(placeShortResults);
//
//        return placesResult;
//    }

}
