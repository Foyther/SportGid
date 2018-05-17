package android.kfu.service.api.converter;

import android.kfu.entities.Place;
import android.kfu.service.api.response.PlaceInfoResult;

import javax.persistence.Converter;

/**
 * Created by Nurislam on 17.05.2018.
 */
public interface PlaceToPlaceInfoResultConverter{

    PlaceInfoResult getPlaceInfoResult(Place place);
}
