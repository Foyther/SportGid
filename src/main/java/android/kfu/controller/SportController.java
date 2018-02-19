package android.kfu.controller;

import android.kfu.entities.Event;
import android.kfu.entities.KindOfSport;
import android.kfu.entities.Place;
import android.kfu.service.api.ErrorCodes;
import android.kfu.service.api.EventService;
import android.kfu.service.api.KindOfSportsService;
import android.kfu.service.api.PlaceService;
import android.kfu.service.api.exception.NotFound.EventNotFoundException;
import android.kfu.service.api.exception.NotFound.KindOfSportNotFoundException;
import android.kfu.service.api.exception.NotFound.PlaceNotFoundException;
import android.kfu.service.api.response.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Nurislam on 12.11.2017.
 */
@RestController
@RequestMapping(value = "/api/v1/sport",  produces = MediaType.APPLICATION_JSON_VALUE)
public class SportController {

    @Autowired
    private ErrorCodes errorCodes;

    @Autowired
    private KindOfSportsService service;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/{id}/places", method = RequestMethod.GET)
    public ApiResult sportPlacesByIdAndCity(@PathVariable("id") long id,
                                             String city) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            KindOfSport sport = service.getById(id);
            Set<Place> list = placeService.getAllByTitle(sport, city);
            result.setBody(list);
        } catch (KindOfSportNotFoundException ex){
            result.setCode(errorCodes.getNotFound());
        } catch (PlaceNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        }
        return result;
    }

    @RequestMapping(value = "/{id}/events", method = RequestMethod.GET)
    public ApiResult sportEventsByIdAndCity(@PathVariable("id") long id,
                                             String city) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            KindOfSport sport = service.getById(id);
            Set<Event> list = eventService.getAllByTitle(sport);
            result.setBody(list);
        }catch (KindOfSportNotFoundException e){
            result.setCode(errorCodes.getNotFound());
        }catch (EventNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ApiResult allSports() {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try{
            result.setBody(service.getAll());
        } catch (KindOfSportNotFoundException ex){
            result.setCode(errorCodes.getNotFound());
        }
        return result;
    }

}