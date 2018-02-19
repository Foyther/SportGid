package android.kfu.tools;

import android.kfu.entities.User;

/**
 * Created by Nurislam on 19.12.2017.
 */
public class UserRefresh {

    static public User getUser(User user){
        User temp = new User();
        temp.setName(user.getName());
        temp.setSurname(user.getSurname());
        temp.setEmail(user.getEmail());
        temp.setPlaces(user.getPlaces());
        temp.setCity(user.getCity());
        temp.setAvatar(user.getAvatar());
        temp.setComplaint(user.getComplaint());
        temp.setReviews(user.getReviews());
        temp.setId(user.getId());
        temp.setToken(user.getToken());
        return user;
    }
}
