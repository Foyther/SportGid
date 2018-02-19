package android.kfu.service.api;

import android.kfu.entities.UserToken;
import android.kfu.service.api.exception.DeadAccessTokenException;
import android.kfu.service.api.exception.NotFound.UserNotFoundException;

/**
 * Created by Nurislam on 22.12.2017.
 */
public interface UserTokenService {
    UserToken getById(Long id) throws UserNotFoundException, DeadAccessTokenException;

    UserToken getByAccessToken(String accessToken) throws UserNotFoundException, DeadAccessTokenException;
}
