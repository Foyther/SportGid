/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.service.api;

import android.kfu.service.api.exception.InvalidFormException;
import android.kfu.service.api.exception.NotFound.UserNotFoundException;
import android.kfu.entities.User;
import android.kfu.form.EditProfileForm;
import android.kfu.service.api.exception.DeadAccessTokenException;
import android.kfu.service.api.response.ProfileResult;

public interface UserService {
    
    User getById(Long id) throws UserNotFoundException;
    
    User getByAccessToken(String accessToken) throws DeadAccessTokenException, UserNotFoundException;
    
    ProfileResult getPojoById(Long id, boolean showPlaces) throws UserNotFoundException;
    
    ProfileResult getPojoByAccessToken(String accessToken) throws DeadAccessTokenException, UserNotFoundException;
    
    void editUser(String accessToken, EditProfileForm editProfileForm) throws DeadAccessTokenException, UserNotFoundException, InvalidFormException;
    void save(User user) throws UserNotFoundException;
}
