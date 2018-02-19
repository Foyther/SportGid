/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.service.api.response;

import java.util.Set;

public class PlacesResult {
    
    private Set<PlaceShortResult> places;

    public Set<PlaceShortResult> getPlaces() {
        return places;
    }

    public void setPlaces(Set<PlaceShortResult> places) {
        this.places = places;
    }
}
