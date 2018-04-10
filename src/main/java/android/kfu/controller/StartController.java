package android.kfu.controller;

import android.kfu.entities.KindOfSport;
import android.kfu.entities.Place;
import android.kfu.service.api.KindOfSportsService;
import android.kfu.service.api.PlaceService;
import android.kfu.service.api.exception.NotFound.PlaceNotFoundException;
import android.kfu.service.api.impl.PlaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by Nurislam on 18.12.2017.
 */
@RestController
@RequestMapping("/")
public class StartController {
    @Autowired
    PlaceService placeService;

    @Autowired
    private KindOfSportsService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sportPlacesByIdAndCity(){
        return "New page";
    }

//    public void deletePlaceById(int i) throws PlaceNotFoundException {
//        placeService.deleteById(10l);
//    }

    public void addSports(String name, String photo){
        KindOfSport sport = new KindOfSport(name, photo);
        service.save(sport);
    }
}

