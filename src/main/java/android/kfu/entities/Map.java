package android.kfu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Nurislam on 12.05.2018.
 */
@Entity
@Table(name = "map")
public class Map implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Double x;

    @Column
    private Double y;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    @OneToMany(mappedBy = "map", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Place> places;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    @OneToMany(mappedBy = "map", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Event> events;

    public Map() {
    }

    public Map(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Map)) return false;

        Map map = (Map) o;

        if (!getId().equals(map.getId())) return false;
        if (!getX().equals(map.getX())) return false;
        return getY().equals(map.getY());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getX().hashCode();
        result = 31 * result + getY().hashCode();
        return result;
    }
}
