package android.kfu.controller;

import android.kfu.entities.*;
import android.kfu.service.api.*;
import android.kfu.service.api.checking.IsSubscribedService;
import android.kfu.service.api.converter.EventToEventInfoResultConverter;
import android.kfu.service.api.exception.AccessDeniedException;
import android.kfu.service.api.exception.DeadAccessTokenException;
import android.kfu.service.api.exception.NotFound.EventNotFoundException;
import android.kfu.service.api.exception.NotFound.PlaceNotFoundException;
import android.kfu.service.api.exception.NotFound.UserNotFoundException;
import android.kfu.service.api.response.ApiResult;
import android.kfu.service.api.response.EventInfoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.HeaderParam;
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
    private MapService mapService;

    @Autowired
    private IsSubscribedService isSubscribedService;

    @Autowired
    private UserService userService;

    @Autowired
    private KindOfSportsService kindOfSportsService;

    @Autowired
    private EventToEventInfoResultConverter eventToEventInfoResultConverter;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ApiResult event(@PathVariable("id") long id,
                           String token) {
        ApiResult apiResult = new ApiResult(errorCodes.getSuccess());
        try {
            Event event = eventService.getById(id);
            EventInfoResult result = eventToEventInfoResultConverter.getEventInfoResult(event);
            result.setSubscribed(isSubscribedService.isSubscribed(event, userService.getByAccessToken(token)));
            apiResult.setBody(result);
        } catch (EventNotFoundException e) {
            apiResult.setCode(errorCodes.getNotFound());
        } catch (UserNotFoundException e) {
            apiResult.setCode(errorCodes.getNotFound());
        } catch (DeadAccessTokenException e) {
            e.printStackTrace();
        }
        return apiResult;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResult add(String price,
                         String title,
                         String maxOfMembers,
                         String token,
                         String photo,
                         String description,
                         Double x,
                         Double y,
                         Long place,
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
            Map map = new Map();
            if (place == null) {
                map = new Map(x, y);
                if (map.getX() == null || map.getY() == null) {
                    throw new PlaceNotFoundException();
                }
                mapService.save(map);

            } else {
                Place somePlace = placeService.getById(place);
                event.setPlace(somePlace);
                if (place != null) {
                    Long id = somePlace.getMap().getId();
                    map = mapService.getById(id);
                }
            }
            event.setMap(map);
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
        } catch (PlaceNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public ApiResult deletePlace(@PathVariable("id") long id,
                                 String token) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            User user = userService.getByAccessToken(token);
            if (user != null) {
                if (user.getId().equals(eventService.getById(id).getAvtor().getId())) {
                    eventService.deleteById(id);
                } else throw new AccessDeniedException();
            }
        } catch (EventNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (UserNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (DeadAccessTokenException e) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
        } catch (AccessDeniedException e) {
            result.setCode(errorCodes.getPermissionDenied());
        }
        return result;
    }

    //TODO
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public ApiResult editEvent(@PathVariable("id") long id,
                                 String token) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            User user = userService.getByAccessToken(token);
            if (user != null) {
                if (user.getId().equals(eventService.getById(id).getAvtor().getId())) {
                    eventService.deleteById(id);
                } else throw new AccessDeniedException();
            }
        } catch (EventNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (UserNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (DeadAccessTokenException e) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
        } catch (AccessDeniedException e) {
            result.setCode(errorCodes.getPermissionDenied());
        }
        return result;
    }

    @RequestMapping(value = "/{id}/subscribe", method = RequestMethod.POST)
    public ApiResult subscribe(@PathVariable("id") long id,
                               String token) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            User user = userService.getByAccessToken(token);
            if (user != null) {
                Event event = eventService.getById(id);
                if (event != null) {
                    if (!isSubscribedService.isSubscribed(event, user)) {
                        Set<Event> events = user.getEvents();
                        events.add(event);
                        user.setEvents(events);
                        userService.save(user);
                    } else throw new AccessDeniedException();
                } else throw new EventNotFoundException();
            }
        } catch (EventNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (UserNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (DeadAccessTokenException e) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
        } catch (AccessDeniedException e) {
            result.setCode(errorCodes.getPermissionDenied());
        }
        return result;
    }

    @RequestMapping(value = "/{id}/unsubscribe", method = RequestMethod.POST)
    public ApiResult unsubscribe(@PathVariable("id") long id,
                                 String token) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            User user = userService.getByAccessToken(token);
            if (user != null) {
                Event event = eventService.getById(id);
                if(event != null) {
                    if (isSubscribedService.isSubscribed(event, user)) {
                        Set<Event> events = user.getEvents();
                        events.remove(event);
                        user.setEvents(events);
                        userService.save(user);
                    } else throw new AccessDeniedException();
                } else throw new EventNotFoundException();
            }
        } catch (EventNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (UserNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (DeadAccessTokenException e) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
        } catch (AccessDeniedException e) {
            result.setCode(errorCodes.getPermissionDenied());
        }
        return result;
    }

    private Set<KindOfSport> getKindOfSports(Long i) {
        List<Long> longSet = new LinkedList<>();
        longSet.add(i);
        return debilofkusok(longSet);
    }

    private Set<KindOfSport> debilofkusok(List<Long> yaUzheNeChelovek) {
        Set<KindOfSport> set = new HashSet<>();
        for (Long i : yaUzheNeChelovek) {
            set.add(kindOfSportsService.getById(i));
        }
        return set;
    }

}
