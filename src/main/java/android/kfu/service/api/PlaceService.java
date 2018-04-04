/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.service.api;

import android.kfu.entities.KindOfSport;
import android.kfu.entities.Place;
import android.kfu.service.api.exception.NotFound.PlaceNotFoundException;

import java.util.Set;

public interface PlaceService{

    public Place getById(Long id) throws PlaceNotFoundException;
    public void save(Place place) throws PlaceNotFoundException;
    public Set<Place> getAllByTitle(KindOfSport sport, String city) throws PlaceNotFoundException;
    public void deleteById(Long i) throws PlaceNotFoundException;
}
