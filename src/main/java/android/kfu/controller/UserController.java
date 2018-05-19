/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.controller;

import android.kfu.entities.Event;
import android.kfu.entities.KindOfSport;
import android.kfu.entities.Place;
import android.kfu.entities.User;
import android.kfu.form.EditProfileForm;
import android.kfu.service.Crypter;
import android.kfu.service.api.ErrorCodes;
import android.kfu.service.api.KindOfSportsService;
import android.kfu.service.api.UserTokenService;
import android.kfu.service.api.auth.AuthService;
import android.kfu.service.api.converter.EventToEventInfoResultConverter;
import android.kfu.service.api.converter.PlaceToPlaceInfoResultConverter;
import android.kfu.service.api.exception.InvalidFormException;
import android.kfu.service.api.UserService;
import android.kfu.service.api.exception.DeadAccessTokenException;
import android.kfu.service.api.exception.NotFound.EventNotFoundException;
import android.kfu.service.api.exception.NotFound.KindOfSportNotFoundException;
import android.kfu.service.api.exception.NotFound.PlaceNotFoundException;
import android.kfu.service.api.exception.NotFound.UserNotFoundException;
import android.kfu.service.api.response.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    private ErrorCodes errorCodes;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private KindOfSportsService kindOfSportsService;

    @Autowired
    private EventToEventInfoResultConverter eventToEventInfoResultConverter;

    @Autowired
    private PlaceToPlaceInfoResultConverter placeToPlaceInfoResultConverter;

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ApiResult profile(String token) {

        ApiResult result = new ApiResult(errorCodes.getSuccess());

        try {
            User user = userService.getByAccessToken(token);
            if (user != null) {
                result.setBody(user);
            } else {
                result.setCode(errorCodes.getNotFound());
            }
        } catch (DeadAccessTokenException ex) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UserNotFoundException ex) {
            result.setCode(errorCodes.getPermissionDenied());
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @RequestMapping(value = "/change_city", method = RequestMethod.POST)
    public ApiResult editProfile(String token,
                                 String city) {

        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            if (authService.isAccessTokenActive(token)) {
                User user = userService.getByAccessToken(token);
                user.setCity(city);
                userService.save(user);
            }
        } catch (DeadAccessTokenException ex) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UserNotFoundException ex) {
            result.setCode(errorCodes.getPermissionDenied());
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResult user(@PathVariable("id") long id) throws UserNotFoundException {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            User user = userService.getById(id);
            result.setBody(user);
        } catch (UserNotFoundException ex) {
            result.setCode(errorCodes.getNotFound());
        }
        return result;
    }

    @RequestMapping(value = "/my_events", method = RequestMethod.POST)
    public ApiResult myEvents(String token) throws UserNotFoundException {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            User user = userService.getByAccessToken(token);
            MyEventsResult eventsResult = new MyEventsResult();
            eventsResult.setEvents(getSetEvent(user.getMyEvents()));
            result.setBody(eventsResult);
        } catch (UserNotFoundException | EventNotFoundException ex) {
            result.setCode(errorCodes.getNotFound());
        } catch (DeadAccessTokenException e) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
        }
        return result;
    }

    @RequestMapping(value = "/my_places", method = RequestMethod.POST)
    public ApiResult myPlaces(String token) throws UserNotFoundException {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            User user = userService.getByAccessToken(token);
            MyPlacesResult placesResult = new MyPlacesResult();
            placesResult.setPlaces(getSetPlace(user.getPlaces()));
            result.setBody(placesResult);
        } catch (UserNotFoundException | PlaceNotFoundException ex) {
            result.setCode(errorCodes.getNotFound());
        } catch (DeadAccessTokenException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/interest", method = RequestMethod.POST)
    public ApiResult addInterest(String token,
                                 @RequestParam("interest") List<Long> interest) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            User user = userService.getByAccessToken(token);
            user.setInterest(debilofkusok(interest));
            userService.save(user);
        } catch (KindOfSportNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (UserNotFoundException e) {
            result.setCode(errorCodes.getPermissionDenied());
        } catch (DeadAccessTokenException e) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
        }
        return result;
    }

    private Set<KindOfSport> debilofkusok(List<Long> yaUzheNeChelovek) {
        Set<KindOfSport> set = new HashSet<>();
        for (Long i : yaUzheNeChelovek) {
            set.add(kindOfSportsService.getById(i));
        }
        return set;
    }

    private Set<EventInfoResult> getSetEvent(Set<Event> events) throws EventNotFoundException {
        if(events == null){
            throw new EventNotFoundException();
        }
        Set<EventInfoResult> results = new HashSet<>();
        for(Event event: events){
            results.add(eventToEventInfoResultConverter.getEventInfoResult(event));
        }
        return results;
    }

    private Set<PlaceInfoResult> getSetPlace(Set<Place> places) throws PlaceNotFoundException {
        if(places == null){
            throw new PlaceNotFoundException();
        }
        Set<PlaceInfoResult> results = new HashSet<>();
        for(Place place: places){
            results.add(placeToPlaceInfoResultConverter.getPlaceInfoResult(place));
        }
        return results;
    }


}
