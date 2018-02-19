/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.tools;

import android.kfu.entities.Place;
import android.kfu.entities.User;
import android.kfu.service.api.response.PlaceShortResult;

/**
 *
 * @author rtmss
 */
public interface PlaceToPlaceShortResultConverter {
    
    PlaceShortResult convert(User user, Place place);
    
}
