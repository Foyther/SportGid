package android.kfu.controller;

import android.kfu.entities.Event;
import android.kfu.entities.KindOfSport;
import android.kfu.entities.Place;
import android.kfu.entities.User;
import android.kfu.service.api.*;
import android.kfu.service.api.exception.DeadAccessTokenException;
import android.kfu.service.api.exception.NotFound.EventNotFoundException;
import android.kfu.service.api.exception.NotFound.PlaceNotFoundException;
import android.kfu.service.api.exception.NotFound.UserNotFoundException;
import android.kfu.service.api.response.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Nurislam on 12.11.2017.
 */
@RestController
@RequestMapping(value = "/api/v1/event")
public class EventController {
    @Autowired
    private ErrorCodes errorCodes;

    @Autowired
    private EventService eventService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private UserService userService;

    @Autowired
    private KindOfSportsService kindOfSportsService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResult event(@PathVariable("id") long id) {
        ApiResult apiResult = new ApiResult(errorCodes.getSuccess());
        try {
            Event event = eventService.getById(id);
            eventService.save(event);
            apiResult.setBody(event);
        } catch (EventNotFoundException e) {
            apiResult.setCode(errorCodes.getNotFound());
        }
        return apiResult;
    }

    //TODO add Place
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResult add(String price,
                         String title,
                         String maxOfMembers,
                         String token,
                         String photo,
                         String description,
                         String address,
                         Long sport) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            Event event = new Event();
            event.setPrice(Integer.valueOf(price));
            event.setTitle(title);
            event.setMaxOfMembers(Integer.valueOf(maxOfMembers));
            event.setAvtor(userService.getByAccessToken(token));
            event.setPhoto(photo);
            event.setDescription(description);
            event.setMembers(new HashSet<User>());
//            Place temp = new Place();
//            temp.setAddress(address);
//            temp.setSport(getKindOfSports(sport));
//            placeService.save(temp);
//            event.setPlace(temp);
            event.setSport(kindOfSportsService.getById(sport));
            eventService.save(event);
        } catch (NumberFormatException ex) {
            result.setCode(errorCodes.getInvalidForm());
        } catch (UserNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (DeadAccessTokenException e) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
//        } catch (PlaceNotFoundException e) {
//            e.printStackTrace();
        }
        return result;
    }

    //TODO string token
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public ApiResult deletePlace(@PathVariable("id") long id,
                                 String token) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            User user = userService.getByAccessToken(token);
            if(user != null){
                if(user.getId().equals(eventService.getById(id).getAvtor().getId())) {
                    eventService.deleteById(id);
                }
            }
        } catch (EventNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (UserNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (DeadAccessTokenException e) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
        }
        return result;
    }

    @RequestMapping(value = "/{id}/subscribe", method = RequestMethod.POST)
    public ApiResult subscribe(@PathVariable("id") long id,
                                 String token) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            User user = userService.getByAccessToken(token);
            if(user != null){
                Event event = eventService.getById(id);
                Set<Event> events = user.getEvents();
                events.add(event);
                user.setEvents(events);
                userService.save(user);
            }
        } catch (EventNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (UserNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (DeadAccessTokenException e) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
        }
        return result;
    }

    public Set<KindOfSport> getKindOfSports(Long i){
        List<Long> longSet = new LinkedList<>();
        longSet.add(i);
        return debilofkusok(longSet);
    }

    private Set<KindOfSport> debilofkusok(List<Long> yaUzheNeChelovek){
        Set<KindOfSport> set = new HashSet<>();
        for (Long i: yaUzheNeChelovek){
            set.add(kindOfSportsService.getById(i));
        }
        return set;
    }
}
