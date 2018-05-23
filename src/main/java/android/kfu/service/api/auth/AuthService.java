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
import org.springframework.stereotype.Service;


@Service
public interface AuthService {

    UserToken registration(RegistrationForm registrationForm, boolean social) throws IncorrectRegistrationFormException, UserWithSameEmailAlreadyExistsException;

    boolean isAccessTokenActive(String accessToken);
    
    User getUserByAccessToken(String accessToken) throws DeadAccessTokenException;
    
    UserToken getToken(String refreshToken) throws DeadAccessTokenException;
    
    UserToken getToken(LoginForm loginForm) throws IncorrectLoginDataException;
    
   }
