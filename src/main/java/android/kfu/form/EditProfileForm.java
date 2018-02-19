/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.form;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author rtmss
 */
public class EditProfileForm {
    
//    “about”:”I am the beast!”,
//    “gender”:”none”,
//   “name”:”Mega Super Ivan”,
//   “home_country”:”Russia”,
//   “home_city”:”Kazan”,
//   “email”:”abcd@mail.ru”,
//   “avatar”:”byte_code.....”,
//   “password_old”:”123456”,
//   “password_new”:”654321”
    
    private String about;
    
    private String gender;
    
    private String name;
    
    @JsonProperty(value = "home_country")
    private String homeCountry;
    
    @JsonProperty(value = "home_city")
    private String homeCity;
    
    //TODO
    //avatar
    
    private String email;
    
    @JsonProperty(value = "password_old")
    private String passwordOld;
    
    @JsonProperty(value = "password_new")
    private String passwordNew;

    public EditProfileForm() {
    }
    
    

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomeCountry() {
        return homeCountry;
    }

    public void setHomeCountry(String homeCountry) {
        this.homeCountry = homeCountry;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }
    
    

}
