package android.kfu.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Nurislam on 12.05.2018.
 */
@Entity
@Table
public class Map implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Double x;

    @Column
    private Double y;

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
}
