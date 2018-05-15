package android.kfu.service.api.response;

import java.io.Serializable;

/**
 * Created by Nurislam on 15.05.2018.
 */
public class UserShortResult implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String avatar;

    public UserShortResult() {
    }

    public UserShortResult(Long id, String name, String surname, String avatar) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.avatar = avatar;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
