package android.kfu.service.api.response;

import android.kfu.entities.Event;
import android.kfu.entities.Place;
import android.kfu.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.jws.soap.SOAPBinding;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Nurislam on 17.05.2018.
 */
public class ReviewResult {

    private Long id;
    private Long date;
    private String body;
    private int rating;
    UserShortResult user;

    public ReviewResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UserShortResult getUser() {
        return user;
    }

    public void setUser(UserShortResult user) {
        this.user = user;
    }
}
