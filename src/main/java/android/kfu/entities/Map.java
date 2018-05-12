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
    private int x;

    @Column
    private int y;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
