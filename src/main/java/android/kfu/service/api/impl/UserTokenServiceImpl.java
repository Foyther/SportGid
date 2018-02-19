package android.kfu.service.api.impl;

import android.kfu.entities.UserToken;
import android.kfu.repository.UserTokenRepository;
import android.kfu.service.api.UserTokenService;
import android.kfu.service.api.exception.DeadAccessTokenException;
import android.kfu.service.api.exception.NotFound.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nurislam on 22.12.2017.
 */
@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    UserTokenRepository userTokenRepository;

    @Override
    public UserToken getById(Long id) throws UserNotFoundException, DeadAccessTokenException {
        if (id == null) {
            throw new UserNotFoundException();
        }
        UserToken userToken = userTokenRepository.findOne(id);
        if (userToken != null) {
            return userToken;
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public UserToken getByAccessToken(String accessToken) throws UserNotFoundException, DeadAccessTokenException {
        if (accessToken == null) {
            throw new UserNotFoundException();
        }
        UserToken userToken = userTokenRepository.findOneByAccessToken(accessToken);
        if (userToken != null) {
            return userToken;
        } else {
            throw new UserNotFoundException();
        }
    }
}
