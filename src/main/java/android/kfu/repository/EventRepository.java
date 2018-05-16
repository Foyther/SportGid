package android.kfu.repository;

import android.kfu.entities.Event;
import android.kfu.entities.KindOfSport;
import android.kfu.entities.Place;
import android.kfu.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by Nurislam on 19.12.2017.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
    @Query(value = "SELECT * FROM events, places WHERE events.sports = ?1 AND places.city = ?2 ORDER BY events.rating",nativeQuery = true)
    Set<Event> findAllOrderedByRating(KindOfSport sport, String city);

    @Query(value = "SELECT * FROM events WHERE events.sports = ?1 AND events.city = ?2 ORDER BY events.rating DESC",nativeQuery = true)
    Set<Event> findAllOrderedByRatingDesc(KindOfSport sport, String city);

    @Query(value = "SELECT * FROM events, places WHERE events.sports = ?1 AND places.city = ?2 AND places.id = events.places",nativeQuery = true)
    Set<Event> findAllOrderedByTitle(KindOfSport sport, String city);

    @Query(value = "SELECT * FROM events WHERE events.sports = ?1 AND events.city = ?2 ORDER BY events.title DESC",nativeQuery = true)
    Set<Event> findAllOrderedByTitleDesc(KindOfSport sport, String city);

    @Query(value = "SELECT * FROM events WHERE events.sports = ?1 AND events.city = ?2 ORDER BY events.price",nativeQuery = true)
    Set<Event> findAllOrderedByPrice(KindOfSport sport, String city);

    @Query(value = "SELECT * FROM events WHERE events.sports = ?1 AND events.city = ?2 ORDER BY events.price DESC",nativeQuery = true)
    Set<Event> findAllOrderedByPriceDesc(KindOfSport sport, String city);

    Set<Event> findAllBySport(KindOfSport sport);

    void deleteEventById(Long id);

    Set<Event> findAllBySportAndPriceBetween(KindOfSport sport, int price1, int price2);
}
