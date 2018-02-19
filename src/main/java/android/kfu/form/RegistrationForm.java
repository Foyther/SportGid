/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.form;

import android.kfu.entities.User;
import android.kfu.service.Crypter;

public class RegistrationForm {
    
    private String name;
    private String surname;
    private String email;
    private String password;
    private String city;

    public RegistrationForm() {
    }

    public RegistrationForm(String name, String surname, String email, String password, String city) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public User generateUser(Crypter crypter) {
        User user = new User();
        user.setEmail(email);
        user.setPasswordCrypt(crypter.crypt(password));
        user.setName(name);
        user.setSurname(surname);
        user.setCity(city);
        return user;
    }
    
}
