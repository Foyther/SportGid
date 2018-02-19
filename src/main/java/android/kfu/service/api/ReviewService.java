package android.kfu.service.api;

import android.kfu.entities.Review;
import android.kfu.service.api.exception.NotFound.PlaceNotFoundException;
import android.kfu.service.api.exception.NotFound.ReviewNotFoundException;

import java.util.Set;

/**
 * Created by Nurislam on 20.12.2017.
 */
public interface ReviewService {
    Set<Review> getAllByPlace(Long id) throws PlaceNotFoundException, ReviewNotFoundException;

    void save(Review review) throws ReviewNotFoundException;
}
