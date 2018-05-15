package android.kfu.service.api.checking;

import android.kfu.entities.Event;
import android.kfu.entities.User;
import android.kfu.service.api.exception.NotFound.UserNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Nurislam on 15.05.2018.
 */
@Service
public class IsSubscribedService {

    public boolean isSubscribed(Event event, User user) throws UserNotFoundException {
        if (user != null) {
            if (event.getMembers().size() > 0) {
                for (User temp : event.getMembers()) {
                    if (temp.getId() == user.getId()) {
                        return true;
                    }
                }
            } else return false;
        }
        throw new UserNotFoundException();
    }
}
