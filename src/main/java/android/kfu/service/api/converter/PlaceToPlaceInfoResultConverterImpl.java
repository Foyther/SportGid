package android.kfu.service.api.converter;

import android.kfu.entities.BookingEntry;
import android.kfu.entities.KindOfSport;
import android.kfu.entities.Place;
import android.kfu.entities.Review;
import android.kfu.service.api.response.PlaceInfoResult;
import android.kfu.service.api.response.ReviewResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nurislam on 17.05.2018.
 */
@Component
public class PlaceToPlaceInfoResultConverterImpl implements PlaceToPlaceInfoResultConverter {

    @Autowired
    UserToUserShortResultConverter userToUserShortResultConverter;

    @Autowired
    ReviewToReviewResult reviewToReviewResult;

    @Override
    public PlaceInfoResult getPlaceInfoResult(Place place){
        if(place == null){
            return null;
        }
        PlaceInfoResult result = new PlaceInfoResult();
        result.setId(place.getId());
        result.setAddress(place.getAddress());
        result.setCity(place.getCity());
        result.setContact(place.getContact());
        result.setDescription(place.getDescription());
        result.setPhoto(place.getPhoto());
        result.setPrice(place.getPrice());
        result.setRating(place.getRating());
        result.setTitle(place.getTitle());
        result.setUser(userToUserShortResultConverter.getUserShortResult(place.getUser()));
        result.setMap(place.getMap().getId());
        Set<Long> bookingSet = new HashSet<>();
        for(BookingEntry entry: place.getBookingEntrys()){
            bookingSet.add(entry.getId());
        }
        result.setBookingEntrys(bookingSet);
        Set<Long> sportsSet = new HashSet<>();
        for(KindOfSport sport: place.getSport()){
            sportsSet.add(sport.getId());
        }
        result.setSport(sportsSet);

        Set<ReviewResult> reviewResults = new HashSet<>();
        for(Review review: place.getReviews()){
            reviewResults.add(reviewToReviewResult.getReviewResult(review));
        }
        result.setReviews(reviewResults);

        return result;
    }
}
