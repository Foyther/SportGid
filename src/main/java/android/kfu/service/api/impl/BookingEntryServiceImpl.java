package android.kfu.service.api.impl;

import android.kfu.entities.BookingEntry;
import android.kfu.repository.BookingEntryRepository;
import android.kfu.service.api.BookingEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nurislam on 14.05.2018.
 */
@Service
public class BookingEntryServiceImpl implements BookingEntryService {
    @Autowired
    private BookingEntryRepository bookingEntryRepository;

    @Override
    public void save(BookingEntry bookingEntry) {
        if(bookingEntry != null){
            bookingEntryRepository.save(bookingEntry);
        }
    }
}
