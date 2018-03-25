/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.controller;

import android.kfu.entities.KindOfSport;
import android.kfu.entities.Place;
import android.kfu.entities.Review;
import android.kfu.entities.User;
import android.kfu.service.api.*;
import android.kfu.service.api.exception.DeadAccessTokenException;
import android.kfu.service.api.exception.NotFound.PlaceNotFoundException;
import android.kfu.service.api.exception.NotFound.ReviewNotFoundException;
import android.kfu.service.api.exception.NotFound.UserNotFoundException;
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
    private ReviewService reviewService;

    @Autowired
    private KindOfSportsService kindOfSportsService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResult place(@PathVariable("id") long id) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            Place place = placeService.getById(id);
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
                         @RequestParam("sport") List<Long> sport) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            Place place = new Place();
            List<Long> longs = sport;
            place.setSport(debilofkusok(sport));
            place.setAddress(address);
            place.setContact(contact);
            place.setTitle(title);
            place.setDescription(description);
            place.setCity(city);
            place.setPhoto(photo);
            placeService.save(place);
        } catch (PlaceNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Set<KindOfSport> debilofkusok(List<Long> yaUzheNeChelovek){
        Set<KindOfSport> set = new HashSet<>();
        for (Long i: yaUzheNeChelovek){
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

    private Place refreshRating(Place place, int i){
        for(Review review: place.getReviews()){
            i+=review.getRating();
        }
        int rating = i/(place.getReviews().size()+1);
        place.setRating(rating);
        return place;
    }
}
