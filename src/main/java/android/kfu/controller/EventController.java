package android.kfu.controller;

import android.kfu.entities.Event;
import android.kfu.service.api.*;
import android.kfu.service.api.exception.NotFound.EventNotFoundException;
import android.kfu.service.api.exception.NotFound.PlaceNotFoundException;
import android.kfu.service.api.response.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nurislam on 12.11.2017.
 */
@RestController
@RequestMapping(value = "/api/v1/event")
public class EventController {
    @Autowired
    private ErrorCodes errorCodes;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private EventService eventService;

    @Autowired
    private KindOfSportsService kindOfSportsService;

    @Autowired
    private KindOfSportsService kk;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResult event(@PathVariable("id") long id) {
        ApiResult apiResult = new ApiResult(errorCodes.getSuccess());
        try {
            Event event  = eventService.getById(id);
            eventService.save(event);
            apiResult.setBody(event);
        } catch (EventNotFoundException e) {
            apiResult.setCode(errorCodes.getNotFound());
        }
        return apiResult;
    }

    //TODO max of members
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResult add(String price,
                         String rating,
                         String title,
                         String description) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
        Event event = new Event();
        event.setPrice(Integer.valueOf(price));
        event.setRating(Integer.valueOf(rating));
        event.setTitle(title);
        event.setDescription(description);
        event.setSport(kindOfSportsService.getById(1l));
        eventService.save(event);
        } catch (NumberFormatException ex){
            result.setCode(errorCodes.getInvalidForm());
        }
        return result;
    }
}
