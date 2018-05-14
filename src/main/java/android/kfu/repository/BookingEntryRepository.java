package android.kfu.repository;

import android.kfu.entities.BookingEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nurislam on 14.05.2018.
 */
@Repository
public interface BookingEntryRepository extends JpaRepository<BookingEntry, Long> {

}
