/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.service.api.impl;

import android.kfu.entities.User;
import android.kfu.form.EditProfileForm;
import android.kfu.form.validator.EditProfileFormValidator;
import android.kfu.repository.UserRepository;
import android.kfu.service.Crypter;
import android.kfu.service.api.exception.InvalidFormException;
import android.kfu.service.api.UserService;
import android.kfu.service.api.auth.AuthService;
import android.kfu.service.api.exception.DeadAccessTokenException;
import android.kfu.service.api.exception.NotFound.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private EditProfileFormValidator editProfileFormValidator;
    
    @Autowired
    private Crypter crypter;
    
    @Override
    public User getById(Long id) throws UserNotFoundException {
        if (id == null) {
            throw new UserNotFoundException();
        }
        User user = userRepository.findOne(id);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User getByAccessToken(String accessToken) throws DeadAccessTokenException, UserNotFoundException {
        if (accessToken == null) {
            throw  new DeadAccessTokenException();
        }
        User user = userRepository.findOneByToken(accessToken);
        if(user == null){
            throw new UserNotFoundException();
        }
        return user;
    }



    @Override
    public void editUser(String accessToken, EditProfileForm editProfileForm)
            throws DeadAccessTokenException, UserNotFoundException, InvalidFormException {
        if (editProfileFormValidator.validate(editProfileForm)) {
            if (authService.isAccessTokenActive(accessToken)) {
                User user = authService.getUserByAccessToken(accessToken);
                
                if (editProfileForm.getPasswordOld() != null) {
                    if (!crypter.crypt(editProfileForm.getPasswordOld())
                            .equals(user.getPasswordCrypt())) {
                        throw new InvalidFormException();
                    }
                }
               
                updateUserFromEditeProfileForm(user, editProfileForm);
                
            } else {
                new DeadAccessTokenException();
            }
        } else {
            throw new InvalidFormException();
        }
    }
    
    private void updateUserFromEditeProfileForm(User user, EditProfileForm epf) {
        if (epf.getName() != null) {
            user.setName(epf.getName());
        }
        
        if (epf.getEmail() != null) {
            user.setName(epf.getEmail());
        }
        
        if (epf.getAbout() != null) {
            user.setName(epf.getAbout());
        }
        
        if (epf.getGender() != null) {
            user.setGender(epf.getGender());
        }
        
        if (epf.getHomeCity() != null) {
            user.setCity(epf.getHomeCity());
        }
        if (epf.getPasswordNew() != null) {
            user.setPasswordCrypt(crypter.crypt(epf.getPasswordNew()));
        }
        
        userRepository.save(user);
        
    }

    @Override
    public void save(User user) throws UserNotFoundException {
        if(user != null) {
            userRepository.save(user);
        } else throw new UserNotFoundException();
    }
}
