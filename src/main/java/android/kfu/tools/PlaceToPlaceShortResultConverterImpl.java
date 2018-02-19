/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.tools;

import android.kfu.entities.Review;
import android.kfu.entities.Place;
import android.kfu.entities.User;
import android.kfu.service.api.response.PlaceShortResult;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 *
 * @author rtmss
 */
@Component
public class PlaceToPlaceShortResultConverterImpl implements PlaceToPlaceShortResultConverter{

    @Override
    public PlaceShortResult convert(User user, Place place) {
        
        if (place == null) {
            return null;
        }
        //TODO
//        PlaceShortResult placeShortResult = new PlaceShortResult();
//
//        placeShortResult.setId(place.getId());
//
//        placeShortResult.setTitle(place.getTitle());
//
//        placeShortResult.setLat(place.getLat());
//
//        placeShortResult.setLon(place.getLon());
//
//        Set<PlaceLike> likes = place.getLikes();
//        placeShortResult.setLikes(likes != null ? likes.size() : 0L);
//
//        Set<Review> reviews = place.getReviews();
//        placeShortResult.setComments(reviews != null ? reviews.size() : 0L);
//
//        placeShortResult.setIsPrivate(place.getIsPrivate());
//
//        User owner = place.getUser();
//        if (owner != null) {
//            placeShortResult.setOwnerId(owner.getId());
//        }
//
//        Set<PlacePhoto> photos = place.getPhotos();
//        if (photos != null) {
//            for (PlacePhoto placePhoto : photos) {
//                if (placePhoto != null) {
//                    if (placePhoto.getIdx() != null) {
//                        if (placePhoto.getIdx() == 0) {
//                            placeShortResult.setLogo(placePhoto.getUri());
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//
//        placeShortResult.setLiked(false);
//        if (user != null) {
//            User iUser;
//            for (PlaceLike placeLike : likes) {
//                if (placeLike != null) {
//                    iUser = placeLike.getUser();
//                    if (iUser != null) {
//                        if (iUser.getId().equals(user.getId())) {
//                            placeShortResult.setLiked(true);
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//
        return null;
    }
    
}
