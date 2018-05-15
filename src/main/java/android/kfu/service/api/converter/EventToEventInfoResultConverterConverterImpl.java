package android.kfu.service.api.converter;

import android.kfu.entities.Event;
import android.kfu.entities.User;
import android.kfu.service.api.exception.NotFound.EventNotFoundException;
import android.kfu.service.api.response.EventInfoResult;
import android.kfu.service.api.response.UserShortResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nurislam on 15.05.2018.
 */
@Component
public class EventToEventInfoResultConverterConverterImpl implements EventToEventInfoResultConverter {
    @Autowired
    UserToUserShortResultConverter userToUserShortResultConverter;


    public EventInfoResult getEventInfoResult(Event event) throws EventNotFoundException {
        if(event == null){
            throw new EventNotFoundException();
        }
        EventInfoResult temp = new EventInfoResult();
        temp.setId(event.getId());
        temp.setAvtor(userToUserShortResultConverter.getUserShortResult(event.getAvtor()));
        temp.setDescription(event.getDescription());
        temp.setTitle(event.getTitle());
        temp.setMaxOfMembers(event.getMaxOfMembers());
        temp.setMembers(getSet(event.getMembers()));
        temp.setPhoto(event.getPhoto());
        temp.setPrice(event.getPrice());
        temp.setRating(event.getRating());
        temp.setMap(event.getMap().getId());
        temp.setPlace(event.getPlace().getId());
        temp.setReviews(event.getReviews());
        return temp;
    }

    private Set<UserShortResult> getSet(Set<User> users){
        Set<UserShortResult> userShortResultSet = new HashSet<>();
        for(User user: users){
            userShortResultSet.add(userToUserShortResultConverter.getUserShortResult(user));
        }
        return userShortResultSet;
    }
}
