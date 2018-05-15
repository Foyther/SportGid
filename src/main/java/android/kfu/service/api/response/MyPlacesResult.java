package android.kfu.service.api.response;

import android.kfu.entities.Place;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Nurislam on 15.05.2018.
 */
public class MyPlacesResult implements Serializable {

    private Set<Place> places;

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }
}
