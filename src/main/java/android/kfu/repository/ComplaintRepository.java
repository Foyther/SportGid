package android.kfu.repository;

import android.kfu.entities.Complaint;
import android.kfu.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Nurislam on 27.03.2018.
 */
@Repository
public interface ComplaintRepository  extends JpaRepository<Complaint, Long> {
    Set<Complaint> findAllByPlace(Long id);
}
