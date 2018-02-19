/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.controller;

import android.kfu.entities.User;
import android.kfu.entities.UserToken;
import android.kfu.form.LoginForm;
import android.kfu.form.RegistrationForm;
import android.kfu.service.Crypter;
import android.kfu.service.api.ErrorCodes;
import android.kfu.service.api.UserService;
import android.kfu.service.api.auth.AuthService;
import android.kfu.service.api.exception.DeadAccessTokenException;
import android.kfu.service.api.exception.IncorrectLoginDataException;
import android.kfu.service.api.exception.IncorrectRegistrationFormException;
import android.kfu.service.api.exception.NotFound.UserNotFoundException;
import android.kfu.service.api.exception.UserWithSameEmailAlreadyExistsException;
import android.kfu.service.api.response.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/v1")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @Autowired
    private ErrorCodes errorCodes;

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public ApiResult login(String login,
                           String password) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        LoginForm loginForm = new LoginForm(login, password);
        try {
            UserToken userToken = authService.getToken(loginForm);
            User user = userService.getByAccessToken(userToken.getAccessToken());
            if (userToken != null && user != null) {
//                result.setBody(user);
                result.setBody(userToken);
            } else {
                result.setCode(errorCodes.getNotFound());
            }
        } catch (IncorrectLoginDataException ex) {
            result.setCode(errorCodes.getInvalidLoginOrPassword());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (DeadAccessTokenException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public ApiResult registration(String name,
                                  String surname,
                                  String email,
                                  String password,
                                  String city) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        RegistrationForm form = new RegistrationForm(name,surname, email, password, city);
        try {
            UserToken token = authService.registration(form);
            if (token != null) {
                result.setBody(token);
            } else {
                result.setCode(errorCodes.getNotFound());
            }
        } catch (IncorrectRegistrationFormException ex) {
            result.setCode(errorCodes.getInvalidForm());
            Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (UserWithSameEmailAlreadyExistsException ex) {
            result.setCode(errorCodes.getUserWithSameLoginAlreadyExists());
            Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ApiResult updateToken(String refreshToken) {
        ApiResult result = new ApiResult(errorCodes.getSuccess());
        if (refreshToken != null) {
            try {
                UserToken token = authService.getToken(refreshToken);
                if (token != null) {
                    result.setBody(token);
                } else {
                    result.setCode(errorCodes.getNotFound());
                }
            } catch (DeadAccessTokenException ex) {
                result.setCode(errorCodes.getInvalidOrOldAccessToken());
                Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            result.setCode(errorCodes.getInvalidRequest());
        }
        return result;
    } 
}
