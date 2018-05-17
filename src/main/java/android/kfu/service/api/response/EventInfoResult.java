package android.kfu.service.api.response;

import android.kfu.entities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Nurislam on 15.05.2018.
 */
public class EventInfoResult implements Serializable {

    private Long id;

    private String title;

    private String photo;

    private int rating;

    private int price;

    @JsonProperty(value = "max_of_members")
    private int maxOfMembers;

    private String description;

    @JsonProperty(value = "is_subscribed")
    private boolean subscribed;

    private Long place;

    private Set<Long> sport;

    private UserShortResult avtor;

    private Set<UserShortResult> members;

    private Long map;

    private Set<Review> reviews;

    public EventInfoResult() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Set<Long> getSport() {
        return sport;
    }

    public void setSport(Set<Long> sport) {
        this.sport = sport;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMaxOfMembers() {
        return maxOfMembers;
    }

    public void setMaxOfMembers(int maxOfMembers) {
        this.maxOfMembers = maxOfMembers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPlace() {
        return place;
    }

    public void setPlace(Long place) {
        this.place = place;
    }

    public UserShortResult getAvtor() {
        return avtor;
    }

    public void setAvtor(UserShortResult avtor) {
        this.avtor = avtor;
    }

    public Set<UserShortResult> getMembers() {
        return members;
    }

    public void setMembers(Set<UserShortResult> members) {
        this.members = members;
    }

    public Long getMap() {
        return map;
    }

    public void setMap(Long map) {
        this.map = map;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}
