package android.kfu.repository;

import android.kfu.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Nurislam on 20.12.2017.
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Set<Review> findAllByPlace(Long id);
}
