package android.kfu.service.api.impl;

import android.kfu.entities.Event;
import android.kfu.entities.KindOfSport;
import android.kfu.entities.Place;
import android.kfu.repository.EventRepository;
import android.kfu.service.api.EventService;
import android.kfu.service.api.exception.NotFound.EventNotFoundException;
import android.kfu.service.api.exception.NotFound.PlaceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Nurislam on 19.12.2017.
 */
@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event getById(Long id) throws EventNotFoundException{
        if(id != 0 && eventRepository.findOne(id) != null) {
            return eventRepository.findOne(id);
        } return null;
    }

    @Override
    public void save(Event event) {
        if(event != null) {
            eventRepository.save(event);
        }
    }

    @Override
    public Set<Event> getAllByTitle(KindOfSport sport, String city) throws EventNotFoundException {
        return null;
    }

    @Override
    public Set<Event> getAllByTitle(Place place, KindOfSport sport) throws EventNotFoundException {
        return null;
    }

    @Override
    public Set<Event> getAllBySportAndPriceBetween(KindOfSport sport, int price1, int price2) throws EventNotFoundException{
        return eventRepository.findAllBySportAndPriceBetween(sport, price1, price2);
    }

    @Override
    public Set<Event> getAllBySport(KindOfSport sport) throws EventNotFoundException{
        return eventRepository.findAllBySport(sport);
    }

    @Override
    public void deleteById(Long id) throws EventNotFoundException {
        eventRepository.delete(id);
    }

    @Override
    public Set<Event> getAllByTitleDesc(KindOfSport sport, String city) throws EventNotFoundException{
        return eventRepository.findAllOrderedByTitleDesc(sport, city);
    }

    @Override
    public Set<Event> getAllByRating(KindOfSport sport, String city) throws EventNotFoundException{
        return eventRepository.findAllOrderedByRating(sport, city);
    }

    @Override
    public Set<Event> getAllByRatingDesc(KindOfSport sport, String city) throws EventNotFoundException{
        return eventRepository.findAllOrderedByRatingDesc(sport, city);
    }

    @Override
    public Set<Event> getAllByPrice(KindOfSport sport, String city) throws EventNotFoundException{
        return eventRepository.findAllOrderedByPrice(sport, city);
    }

    @Override
    public Set<Event> getAllByPriceDesc(KindOfSport sport, String city) throws EventNotFoundException{
        return eventRepository.findAllOrderedByPriceDesc(sport, city);
    }
}
