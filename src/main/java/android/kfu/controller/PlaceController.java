/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.controller;

import android.kfu.entities.*;
import android.kfu.entities.Map;
import android.kfu.service.api.checking.IsBookedService;
import android.kfu.service.api.*;
import android.kfu.service.api.exception.DeadAccessTokenException;
import android.kfu.service.api.exception.NotFound.ComplaintNotFoundException;
import android.kfu.service.api.exception.NotFound.PlaceNotFoundException;
import android.kfu.service.api.exception.NotFound.ReviewNotFoundException;
import android.kfu.service.api.exception.NotFound.UserNotFoundException;
import android.kfu.service.api.exception.TimeIsBookedException;
import android.kfu.service.api.response.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/v1/place")
public class PlaceController {

    @Autowired
    private ErrorCodes errorCodes;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private UserService userService;

    @Autowired
    private MapService mapService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private KindOfSportsService kindOfSportsService;

    @Autowired
    private IsBookedService isBookedService;

    @Autowired
    private BookingEntryService bookingEntryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResult place(@PathVariable("id") long id) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            Place place = placeService.getById(id);
            System.out.println("WArniiiiiiinNGGGG ------- " + place.getUser().getEmail());
            System.out.println("to String ------------ " + place.toString());
            result.setBody(place);
        } catch (PlaceNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        }
        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResult add(String address,
                         String contact,
                         String title,
                         String description,
                         String city,
                         String photo,
                         @RequestParam("sport") List<Long> sport,
                         String token) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            Place place = new Place();
            place.setSport(debilofkusok(sport));
            place.setAddress(address);
            place.setContact(contact);
            place.setTitle(title);
            place.setUser(userService.getByAccessToken(token));
            place.setDescription(description);
            place.setCity(city);
            place.setPhoto(photo);
            placeService.save(place);
        } catch (PlaceNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (UserNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (DeadAccessTokenException e) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
        }
        return result;
    }


    //TODO map isn't save to BD
    @RequestMapping(value = "/addMap", method = RequestMethod.POST)
    public ApiResult addNew(String address,
                            String contact,
                            String title,
                            String description,
                            String city,
                            int price,
                            String photo,
                            Double x,
                            Double y,
                            @RequestParam("sport") List<Long> sport,
                            String token) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            Place place = new Place();
            Map map = new Map(x, y);

            place.setSport(debilofkusok(sport));
            place.setAddress(address);
            place.setContact(contact);
            place.setTitle(title);
            place.setPrice(price);
            place.setUser(userService.getByAccessToken(token));
            place.setDescription(description);
            place.setCity(city);
            place.setPhoto(photo);
            Map temp = mapService.getByXAndY(x, y);
            if(temp == null){
                mapService.save(map);
            } else{
                map = temp;
            }
            place.setMap(map);

            placeService.save(place);
        } catch (PlaceNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (UserNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
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

    @RequestMapping(value = "/{id}/review", method = RequestMethod.POST)
    public ApiResult addReview(@PathVariable("id") long id,
                               String token,
                               String body,
                               String rating) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            User temp = userService.getByAccessToken(token);
            Place place = placeService.getById(id);
            Review review = new Review(new Date().getTime(), temp, body, place, Integer.valueOf(rating));
            Set<Review> reviewSet = place.getReviews();
            reviewSet.add(review);
            place.setReviews(reviewSet);
            reviewService.save(review);
            placeService.save(refreshRating(place, Integer.valueOf(rating)));
        } catch (PlaceNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (UserNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (DeadAccessTokenException e) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
        } catch (ReviewNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        }
        return result;
    }

    @RequestMapping(value = "/{id}/complaint", method = RequestMethod.POST)
    public ApiResult addComplaint(@PathVariable("id") long id,
                                  String token,
                                  String body,
                                  String title) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            User temp = userService.getByAccessToken(token);
            Place place = placeService.getById(id);
            Complaint complaint = new Complaint(new Date().getTime(), temp, body, place);
            complaint.setTitle(title);
            Set<Complaint> complaintSet = place.getComplaints();
            complaintSet.add(complaint);
            place.setComplaints(complaintSet);
            complaintService.save(complaint);
        } catch (ComplaintNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (PlaceNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (UserNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (DeadAccessTokenException e) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
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
                if (user.getId().equals(placeService.getById(id).getUser().getId())) {
                    placeService.deleteById(id);
                }
            }
        } catch (PlaceNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (UserNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (DeadAccessTokenException e) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
        }
        return result;
    }

    @RequestMapping(value = "/{id}/book", method = RequestMethod.POST)
    public ApiResult bookPlace(@PathVariable("id") long id,
                               Long beginDate,
                               Long endDate,
                               String token
    ) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            User user = userService.getByAccessToken(token);
            Place place = placeService.getById(id);
            if (user != null) {
                Long now = new Date().getTime();
                if (now < beginDate) {
                    BookingEntry entry = new BookingEntry();
                    entry.setUser(user);
                    entry.setBeginDate(beginDate);
                    entry.setEndDate(Long.valueOf(endDate));
                    if (place.getBookingEntrys() != null) {
                        if (isBookedService.isBooked(place, entry)) {
                            entry.setPlace(place);
                            bookingEntryService.save(entry);
                        }
                    }//TODO isn't book error
                }
                throw new TimeIsBookedException();
            }
        } catch (PlaceNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (UserNotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        } catch (DeadAccessTokenException e) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
        } catch (TimeIsBookedException e) {
            result.setCode(errorCodes.getInvalidOrOldAccessToken());
        }
        return result;
    }

    private Place refreshRating(Place place, int i) {
        for (Review review : place.getReviews()) {
            i += review.getRating();
        }
        int rating = i / (place.getReviews().size() + 1);
        place.setRating(rating);
        return place;
    }
}
