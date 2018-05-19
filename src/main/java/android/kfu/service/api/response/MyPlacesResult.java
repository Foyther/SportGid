package android.kfu.service.api.response;

import android.kfu.entities.Place;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Nurislam on 15.05.2018.
 */
public class MyPlacesResult implements Serializable {

    private Set<PlaceInfoResult> places;

    public MyPlacesResult() {
    }

    public Set<PlaceInfoResult> getPlaces() {
        return places;
    }

    public void setPlaces(Set<PlaceInfoResult> places) {
        this.places = places;
    }
}
