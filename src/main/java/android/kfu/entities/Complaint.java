package android.kfu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Nurislam on 10.11.2017.
 */
@Entity
@Table(name = "complaints")
public class Complaint implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private Long complaintDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users")
    private User user;

    @Column(name = "body")
    private String body;

    @Column(name = "title")
    private String title;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "places")
    private Place place;

    public Complaint(Long complaintDate, User user, String body, Place place) {
        this.complaintDate = complaintDate;
        this.user = user;
        this.body = body;
        this.place = place;
    }

    public Complaint() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(Long complaintDate) {
        this.complaintDate = complaintDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Complaint)) return false;

        Complaint complaint = (Complaint) o;

        if (!getId().equals(complaint.getId())) return false;
        if (!getComplaintDate().equals(complaint.getComplaintDate())) return false;
        if (!getUser().equals(complaint.getUser())) return false;
        if (!getBody().equals(complaint.getBody())) return false;
        return getPlace().equals(complaint.getPlace());
    }

    @Override
    public int hashCode() {
//        int result = getId().hashCode();
        int result = 0;
        result = 31 * result + getComplaintDate().hashCode();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getBody().hashCode();
        result = 31 * result + getPlace().hashCode();
        return result;
    }
}
