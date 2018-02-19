package android.kfu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Nurislam on 10.11.2017.
 */
@Entity
@Table(name = "events")
public class Event implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "photo")
    private String photo;

    @Column(name = "rating")
    private int rating;

    @Column(name = "price")
    private int price;

    @Column(name = "maxOfMembers")
    private int maxOfMembers;

    @Column(name = "description")
    private String description;

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "places")
//    @NotNull
    private Place place;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avtor")
    private User avtor;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> members;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sports")
    private KindOfSport sport;

    @JsonIgnore
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> reviews;

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

    public KindOfSport getSport() {
        return sport;
    }

    public void setSport(KindOfSport sport) {
        this.sport = sport;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public User getAvtor() {
        return avtor;
    }

    public void setAvtor(User avtor) {
        this.avtor = avtor;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        if (getRating() != event.getRating()) return false;
        if (!getId().equals(event.getId())) return false;
        if (!getTitle().equals(event.getTitle())) return false;
        if (!getDescription().equals(event.getDescription())) return false;
        if (!getPlace().equals(event.getPlace())) return false;
        return getReviews().equals(event.getReviews());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getRating();
        result = 31 * result + getDescription().hashCode();
//        result = 31 * result + getPlace().hashCode();
        result = 31 * result + getReviews().hashCode();
        return result;
    }

}
