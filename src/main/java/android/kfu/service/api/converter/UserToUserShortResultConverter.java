package android.kfu.service.api.converter;

import android.kfu.entities.User;
import android.kfu.service.api.response.UserShortResult;

/**
 * Created by Nurislam on 15.05.2018.
 */
public interface UserToUserShortResultConverter {
    public UserShortResult getUserShortResult(User user);
}
