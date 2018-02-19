/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.service.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;

/**
 *
 * @author rtmss
 */
public class ProfileResult {
    
    private Long id;
    
    private String name;
    
    private String email;
    
    @JsonInclude(Include.NON_NULL)
    private String about;
    
    @JsonInclude(Include.NON_NULL)
    @JsonProperty(value = "home_country")
    private String homeCountry;
    
    @JsonInclude(Include.NON_NULL)
    @JsonProperty(value = "home_city")
    private String homeCity;
    
    @JsonInclude(Include.NON_NULL)
    private Set<Long> places;
    
    private Set<Long> friends;

    @JsonProperty(value = "liked_places")
    private Set<Long> likedPlaces;
    
    
    @JsonProperty(defaultValue = "none")
    private String gender;

    public ProfileResult() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
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

    public Set<Long> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Long> places) {
        this.places = places;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Long> getFriends() {
        return friends;
    }

    public void setFriends(Set<Long> friends) {
        this.friends = friends;
    }

    public Set<Long> getLikedPlaces() {
        return likedPlaces;
    }

    public void setLikedPlaces(Set<Long> likedPlaces) {
        this.likedPlaces = likedPlaces;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }    
}
