package android.kfu.service.api.converter;

import android.kfu.entities.Review;
import android.kfu.service.api.response.ReviewResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Nurislam on 18.05.2018.
 */
public interface ReviewToReviewResult {
    @Autowired
    ReviewResult getReviewResult(Review review);
}
