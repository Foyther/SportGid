package android.kfu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "kind_of_sports")
public class KindOfSport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "photo")
    private String photo;

    @JsonIgnore
    @OneToMany(mappedBy = "sport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Event> events;

    @JsonIgnore
    @ManyToMany(mappedBy = "sport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Place> places;

    @JsonIgnore
    @ManyToMany(mappedBy = "interest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> users;

    public KindOfSport() {
    }

    public KindOfSport(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KindOfSport)) return false;

        KindOfSport that = (KindOfSport) o;

        if (!getId().equals(that.getId())) return false;
        if (!getName().equals(that.getName())) return false;
        if (!getPhoto().equals(that.getPhoto())) return false;
        return getEvents().equals(that.getEvents());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getPhoto().hashCode();
        result = 31 * result + getEvents().hashCode();
        return result;
    }
//
//    public void addSet(){
//        events = new HashSet<>();
//        users = new HashSet<>();
//        places = new HashSet<>();
//    }
}
