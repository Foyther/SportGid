package android.kfu.service.api.converter;

import android.kfu.entities.User;
import android.kfu.service.api.response.UserShortResult;
import org.springframework.stereotype.Component;

/**
 * Created by Nurislam on 15.05.2018.
 */
@Component
public class UserToUserShortResultConverterImpl implements UserToUserShortResultConverter {
    @Override
    public UserShortResult getUserShortResult(User user) {
        UserShortResult result = new UserShortResult(user.getId(), user.getName(),user.getSurname(),user.getAvatar());
        return result;
    }
}
