package android.kfu.service.api;

import android.kfu.entities.Complaint;
import android.kfu.entities.Review;
import android.kfu.service.api.exception.NotFound.ComplaintNotFoundException;
import android.kfu.service.api.exception.NotFound.PlaceNotFoundException;
import android.kfu.service.api.exception.NotFound.ReviewNotFoundException;

import java.util.Set;

/**
 * Created by Nurislam on 27.03.2018.
 */
public interface ComplaintService {
    Set<Complaint> getAllByPlace(Long id) throws PlaceNotFoundException, ComplaintNotFoundException;

    void save(Complaint complaint) throws ComplaintNotFoundException;
}
