package android.kfu.controller;

import android.kfu.entities.Complaint;
import android.kfu.entities.Map;
import android.kfu.entities.Place;
import android.kfu.entities.User;
import android.kfu.service.api.ErrorCodes;
import android.kfu.service.api.MapService;
import android.kfu.service.api.PlaceService;
import android.kfu.service.api.UserService;
import android.kfu.service.api.exception.DeadAccessTokenException;
import android.kfu.service.api.exception.NotFound.ComplaintNotFoundException;
import android.kfu.service.api.exception.NotFound.KindOfSportNotFoundException;
import android.kfu.service.api.exception.NotFound.PlaceNotFoundException;
import android.kfu.service.api.exception.NotFound.UserNotFoundException;
import android.kfu.service.api.response.ApiResult;
import android.kfu.service.api.response.ListMapResult;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Nurislam on 19.05.2018.
 */
@RestController
@RequestMapping(value = "/api/v1/")
public class MapController {

    @Autowired
    private ErrorCodes errorCodes;

    @Autowired
    private MapService mapService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ApiResult list(@RequestParam("maps") List<Long> maps) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        try {
            ListMapResult listMap = new ListMapResult();
            listMap.setMaps(mapService.getByListId(maps));
            result.setBody(listMap);
        } catch (NotFoundException e) {
            result.setCode(errorCodes.getNotFound());
        }
        return result;
    }

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public ApiResult addInterest(Long id) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        Map map = mapService.getById(id);
        result.setBody(map);
        return result;
    }
}
