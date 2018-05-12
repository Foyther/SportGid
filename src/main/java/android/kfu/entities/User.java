/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(
            name = "email",
            unique = true
    )
    private String email;

    @JsonIgnore
    @NotNull
    @Column(name = "password")
    private String passwordCrypt;
    
    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "city")
    private String city;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "gender")
    private String gender;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserToken token;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> reviews;
    
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Place> places;

    @JsonIgnore
    @OneToMany(mappedBy = "avtor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Event> myEvents;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "events")
    private Set<Event> events;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Complaint> complaint;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "sports")
    private Set<KindOfSport> interest;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BookingEntry> bookingEntrys;

    @PrePersist
    public void prePersist() {
        if (getGender()== null){
            setGender(Gender.NONE);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String login) {
        this.email = login;
    }

    public String getPasswordCrypt() {
        return passwordCrypt;
    }

    public void setPasswordCrypt(String passwordCrypt) {
        this.passwordCrypt = passwordCrypt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Event> getEvents() {
        if(events == null){
            events = new HashSet<>();
        }
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<KindOfSport> getInterest() {
        return interest;
    }

    public void setInterest(Set<KindOfSport> interest) {
        this.interest = interest;
    }

    public Set<Complaint> getComplaint() {
        return complaint;
    }

    public void setComplaint(Set<Complaint> complaint) {
        this.complaint = complaint;
    }

    public Set<Event> getMyEvents() {
        return myEvents;
    }

    public void setMyEvents(Set<Event> myEvents) {
        this.myEvents = myEvents;
    }

    public UserToken getToken() {
        return token;
    }

    public void setToken(UserToken token) {
        this.token = token;
    }


    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPasswordCrypt().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getCity().hashCode();
        return result;
    }

    
}