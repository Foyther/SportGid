package android.kfu.service.api.response;

import android.kfu.entities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Nurislam on 17.05.2018.
 */
public class PlaceInfoResult {

    private Long id;
    private String title;
    private int rating;
    private String description;
    private String address;
    private int price;
    private String photo;
    private String city;
    private Long map;
    private UserShortResult user;
    private String contact;
    private Set<Long> sport;
    private Set<ReviewResult> reviews;

    @JsonProperty(value = "booking_entry")
    private Set<Long> bookingEntrys;

    public PlaceInfoResult() {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getMap() {
        return map;
    }

    public void setMap(Long map) {
        this.map = map;
    }

    public UserShortResult getUser() {
        return user;
    }

    public void setUser(UserShortResult user) {
        this.user = user;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Set<Long> getSport() {
        return sport;
    }

    public void setSport(Set<Long> sport) {
        this.sport = sport;
    }

    public Set<ReviewResult> getReviews() {
        return reviews;
    }

    public void setReviews(Set<ReviewResult> reviews) {
        this.reviews = reviews;
    }

    public Set<Long> getBookingEntrys() {
        return bookingEntrys;
    }

    public void setBookingEntrys(Set<Long> bookingEntrys) {
        this.bookingEntrys = bookingEntrys;
    }
}
