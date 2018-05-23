/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.service.api.auth;

import android.kfu.service.api.exception.DeadAccessTokenException;
import android.kfu.service.api.exception.IncorrectLoginDataException;
import android.kfu.service.api.exception.UserWithSameEmailAlreadyExistsException;
import android.kfu.service.api.exception.IncorrectRegistrationFormException;
import android.kfu.entities.User;
import android.kfu.entities.UserToken;
import android.kfu.form.LoginForm;
import android.kfu.form.RegistrationForm;
import android.kfu.form.validator.LoginFormValidator;
import android.kfu.form.validator.RegistrationFormValidator;
import android.kfu.repository.UserRepository;
import android.kfu.repository.UserTokenRepository;
import android.kfu.service.Crypter;
import android.kfu.service.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;


public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private LoginFormValidator loginFormValidator;

    @Autowired
    private Crypter crypter;

    @Autowired
    private RegistrationFormValidator registrationFormValidator;

    private long accessTokenLifeTime;

    public AuthServiceImpl(long accessTokenLifeTime) {
        this.accessTokenLifeTime = accessTokenLifeTime;
    }

    @Override
    public User getUserByAccessToken(String accessToken) throws DeadAccessTokenException {
        UserToken token = userTokenRepository.findOneByAccessToken(accessToken);
        if (token == null) {
            throw new DeadAccessTokenException("token doesn't exist");
        } else {
            if (token.getExpiresIn() < System.currentTimeMillis()) {
                throw new DeadAccessTokenException("token is old");
            } else {
                return token.getUser();
            }
        }
    }

    @Override
    public UserToken getToken(String refreshToken) throws DeadAccessTokenException {
        UserToken token = userTokenRepository.findOneByRefreshToken(refreshToken);
        if (token == null) {
            throw new DeadAccessTokenException("tokenNotFound");
        } else {
            refreshToken(token);
            return token;
        }
    }

    @Override
    public UserToken getToken(LoginForm loginForm) throws IncorrectLoginDataException {
        UserToken token = new UserToken();
        int validation = loginFormValidator.validate(loginForm);
        if (validation == 0) {
            User user = userRepository.findOneByEmail(loginForm.getEmail());
            if (user != null) {
                token = user.getToken();
                if (token != null) {
                    refreshToken(token);
                    return token;
                } else {

                    token = generateToken(user);
                    return token;
                }
            } else {
                throw new IncorrectLoginDataException("user or password incorrect");
            }

        } else if (validation == 1) {
            throw new IncorrectLoginDataException("Login form is invalid");
        } else if(validation == 2){
            throw new IncorrectLoginDataException();
            //TODO this empty;
        } return token;
    }

    @Override
    public UserToken registration(RegistrationForm registrationForm, boolean social) throws IncorrectRegistrationFormException, UserWithSameEmailAlreadyExistsException {

        boolean validation = registrationFormValidator.validate(registrationForm, social);
        if (!validation) {
            throw new IncorrectRegistrationFormException();
        } else {
            User user = userRepository.findOneByEmail(registrationForm.getEmail());
            if (user != null) {
                throw new UserWithSameEmailAlreadyExistsException();
            } else {
                user = registrationForm.generateUser(crypter);
                user = userRepository.save(user);
                UserToken token = generateToken(user);
                return token;
            }
        }

    }

    @Override
    public boolean isAccessTokenActive(String accessToken) {
        UserToken token = userTokenRepository.findOneByAccessToken(accessToken);
        if (token == null) {
            return false;
        } else {
            return token.getExpiresIn() > System.currentTimeMillis();
        }
    }

    private void refreshToken(UserToken token) {
        token.setAccessToken(tokenGenerator.generate());
        token.setRefreshToken(tokenGenerator.generate());
        token.setExpiresIn(System.currentTimeMillis() + accessTokenLifeTime);
        userTokenRepository.save(token);
    }

    //If not exists in db
    private UserToken generateToken(User user) {
        UserToken token = new UserToken();
        token.setUser(user);
        token.setAccessToken(tokenGenerator.generate());
        token.setRefreshToken(tokenGenerator.generate());
        token.setExpiresIn(System.currentTimeMillis() + accessTokenLifeTime);
        token = userTokenRepository.save(token);
        return token;
    }

}
