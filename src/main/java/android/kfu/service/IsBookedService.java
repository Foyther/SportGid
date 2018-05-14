package android.kfu.service;

import android.kfu.entities.BookingEntry;
import android.kfu.entities.Place;
import org.springframework.stereotype.Service;

/**
 * Created by Nurislam on 14.05.2018.
 */
@Service
public class IsBookedService {

    public boolean isBooked(Place place, BookingEntry entry) {
        if (place.getBookingEntrys() != null) {
            for (BookingEntry temp : place.getBookingEntrys()) {
                if (!((temp.getBeginDate() > entry.getEndDate() && temp.getEndDate() > entry.getEndDate()) ||
                        (temp.getBeginDate() < entry.getBeginDate() && temp.getEndDate() < entry.getEndDate()) ||
                        (temp.getBeginDate() == entry.getEndDate()) ||
                        (temp.getEndDate() == entry.getBeginDate()))) {
                    return false;
                }
            }
        }
        return true;
    }
}
