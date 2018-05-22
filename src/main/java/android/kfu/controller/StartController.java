package android.kfu.controller;

import android.kfu.entities.KindOfSport;
import android.kfu.entities.Place;
import android.kfu.service.api.KindOfSportsService;
import android.kfu.service.api.PlaceService;
import android.kfu.service.api.exception.NotFound.PlaceNotFoundException;
import android.kfu.service.api.impl.PlaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by Nurislam on 18.12.2017.
 */
@Controller
public class StartController {

    @Autowired
    private KindOfSportsService service;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String start(){
//        addSports("Футбол", "photo");
//        addSports("Баскетбол", "photo");
//        addSports("Волейбол", "photo");
//        addSports("Гандбол", "photo");
//        addSports("Теннис", "photo");
//        addSports("Пинг-понг", "photo");
        return "politic_conf";
    }

    public void addSports(String name, String photo){
        KindOfSport sport = new KindOfSport(name, photo);
        service.save(sport);
    }
}

