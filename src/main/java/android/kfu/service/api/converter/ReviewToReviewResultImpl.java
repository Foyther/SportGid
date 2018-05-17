package android.kfu.service.api.converter;

import android.kfu.entities.Review;
import android.kfu.service.api.response.ReviewResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nurislam on 18.05.2018.
 */
@Component
public class ReviewToReviewResultImpl implements ReviewToReviewResult{
    @Autowired
    UserToUserShortResultConverter userToUserShortResultConverter;

    @Override
    public ReviewResult getReviewResult(Review review){
        if(review == null){
            return null;
        }
        ReviewResult result = new ReviewResult();
        result.setId(review.getId());
        result.setBody(review.getBody());
        result.setDate(review.getDate());
        result.setRating(review.getRating());
        result.setUser(userToUserShortResultConverter.getUserShortResult(review.getUser()));
        return result;
    }
}
