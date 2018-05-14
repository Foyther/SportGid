package android.kfu.service.api;

import android.kfu.entities.Event;
import android.kfu.entities.KindOfSport;
import android.kfu.entities.Place;
import android.kfu.service.api.exception.NotFound.EventNotFoundException;

import java.util.Set;

/**
 * Created by Nurislam on 19.12.2017.
 */
public interface EventService {

    public Event getById(Long id) throws EventNotFoundException;
    public void save(Event event);
    public Set<Event> getAllByTitle(KindOfSport sport, String city) throws EventNotFoundException;

    Set<Event> getAllByTitle(Place place, KindOfSport sport) throws EventNotFoundException;

    Set<Event> getAllBySportAndPriceBetween(KindOfSport sport, int price1, int price2) throws EventNotFoundException;

    Set<Event> getAllBySport(KindOfSport sport) throws EventNotFoundException;

    void deleteById(Long id) throws EventNotFoundException;

    public Set<Event> getAllByTitleDesc(KindOfSport sport, String city) throws EventNotFoundException;
    public Set<Event> getAllByRating(KindOfSport sport, String city) throws EventNotFoundException;
    public Set<Event> getAllByRatingDesc(KindOfSport sport, String city) throws EventNotFoundException;
    public Set<Event> getAllByPrice(KindOfSport sport, String city) throws EventNotFoundException;
    public Set<Event> getAllByPriceDesc(KindOfSport sport, String city) throws EventNotFoundException;
}
