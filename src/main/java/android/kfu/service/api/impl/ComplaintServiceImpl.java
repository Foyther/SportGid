package android.kfu.service.api.impl;

import android.kfu.entities.Complaint;
import android.kfu.entities.Review;
import android.kfu.repository.ComplaintRepository;
import android.kfu.service.api.ComplaintService;
import android.kfu.service.api.exception.NotFound.ComplaintNotFoundException;
import android.kfu.service.api.exception.NotFound.PlaceNotFoundException;
import android.kfu.service.api.exception.NotFound.ReviewNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Nurislam on 27.03.2018.
 */
@Service
public class ComplaintServiceImpl implements ComplaintService {
    @Autowired
    ComplaintRepository complaintRepository;

    @Override
    public Set<Complaint> getAllByPlace(Long id) throws PlaceNotFoundException, ComplaintNotFoundException {
        Set<Complaint> complaints = complaintRepository.findAllByPlace(id);
        if(complaints == null){
            throw new ComplaintNotFoundException();
        }
        return complaintRepository.findAllByPlace(id);
    }

    @Override
    public void save(Complaint complaint) throws ComplaintNotFoundException {
        if(complaint != null){
            complaintRepository.save(complaint);
        } else throw new ComplaintNotFoundException();
    }
}
