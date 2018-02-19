package android.kfu.service.api.impl;

import android.kfu.entities.Review;
import android.kfu.repository.ReviewRepository;
import android.kfu.service.api.ReviewService;
import android.kfu.service.api.exception.NotFound.ReviewNotFoundException;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Nurislam on 20.12.2017.
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Set<Review> getAllByPlace(Long id) throws ReviewNotFoundException {
        Set<Review> reviews = reviewRepository.findAllByPlace(id);
        if(reviews == null){
            throw new ReviewNotFoundException();
        }
        return reviewRepository.findAllByPlace(id);
    }

    @Override
    public void save(Review review) throws ReviewNotFoundException {
        if(review != null){
            reviewRepository.save(review);
        } else throw new ReviewNotFoundException();
    }
}
