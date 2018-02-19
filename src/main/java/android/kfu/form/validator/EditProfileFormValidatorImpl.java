/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.form.validator;

import android.kfu.entities.Gender;
import android.kfu.form.EditProfileForm;
import android.kfu.tools.EmailValidator;
import android.kfu.tools.StringTool;

/**
 *
 * @author rtmss
 */
public class EditProfileFormValidatorImpl implements EditProfileFormValidator{
    
    private int minNameLength;
    private int maxNameLength;            
    private int minEmailLength;            
    private int maxEmailLength;  
    private int minPasswordLength;            
    private int maxPasswordLength;  
    private int minAboutLength;
    private int maxAboutLength;
    private int minHomeCountryLength;           
    private int maxHomeCountryLength;  
    private int minHomeCityLength;           
    private int maxHomeCityLength;  
    private EmailValidator emailValidator;

    public EditProfileFormValidatorImpl(int minNameLength, int maxNameLength, 
                int minEmailLength, int maxEmailLength, int minPasswordLength, 
                int maxPasswordLength, int minAboutLength, int maxAboutLength, 
                int minHomeCountryLength, int maxHomeCountryLength, 
                int minHomeCityLength, int maxHomeCityLength, EmailValidator emailValidator) {
        this.minNameLength = minNameLength;
        this.maxNameLength = maxNameLength;
        this.minEmailLength = minEmailLength;
        this.maxEmailLength = maxEmailLength;
        this.minPasswordLength = minPasswordLength;
        this.maxPasswordLength = maxPasswordLength;
        this.minAboutLength = minAboutLength;
        this.maxAboutLength = maxAboutLength;
        this.minHomeCountryLength = minHomeCountryLength;
        this.maxHomeCountryLength = maxHomeCountryLength;
        this.minHomeCityLength = minHomeCityLength;
        this.maxHomeCityLength = maxHomeCityLength;
        this.emailValidator = emailValidator;
    }
    
    
    
    @Override
    public boolean validate(EditProfileForm epf) {
        if (epf == null) {
            return false;
        } 
        return validateField(epf.getName(), minNameLength, maxNameLength) &&
                validateField(epf.getHomeCountry(), minHomeCountryLength, maxHomeCountryLength) &&
                validateField(epf.getHomeCity(), minHomeCityLength, maxHomeCityLength) &&
                validateEmail(epf.getEmail()) && 
                validateGender(epf.getGender()) &&
                validatePassword(epf.getPasswordOld(), epf.getPasswordNew());
                
    }
    
    private boolean validateGender(String gender) {
        return gender == null ? true : (gender.equals(Gender.NONE) || 
                                        gender.equals(Gender.MALE) || 
                                        gender.equals(Gender.FEMALE));
    }
    
    private boolean validateField(String field, int minLength, int maxLength) {
        if (field != null) {
            return StringTool.isInBounds(field, minLength, maxLength);
        }
        return true;
    }
    
    private boolean validateEmail(String email) {
        if (email != null) {
            if (!StringTool.isInBounds(email, minEmailLength, maxEmailLength)) {
                return false;
            }
            return emailValidator.validate(email);
        } 
        return true;
    }
    
    private boolean validatePassword(String oldPassword, String newPassword) {
        if ((oldPassword == null) && (newPassword == null)) {
            return true;
        } else {
            return StringTool.isInBounds(oldPassword, minPasswordLength, maxPasswordLength) && 
                    StringTool.isInBounds(newPassword, minPasswordLength, maxPasswordLength);
        }
    }

    public int getMinNameLength() {
        return minNameLength;
    }

    public void setMinNameLength(int minNameLength) {
        this.minNameLength = minNameLength;
    }

    public int getMaxNameLength() {
        return maxNameLength;
    }

    public void setMaxNameLength(int maxNameLength) {
        this.maxNameLength = maxNameLength;
    }

    public int getMinEmailLength() {
        return minEmailLength;
    }

    public void setMinEmailLength(int minEmailLength) {
        this.minEmailLength = minEmailLength;
    }

    public int getMaxEmailLength() {
        return maxEmailLength;
    }

    public void setMaxEmailLength(int maxEmailLength) {
        this.maxEmailLength = maxEmailLength;
    }

    public int getMinPasswordLength() {
        return minPasswordLength;
    }

    public void setMinPasswordLength(int minPasswordLength) {
        this.minPasswordLength = minPasswordLength;
    }

    public int getMaxPasswordLength() {
        return maxPasswordLength;
    }

    public void setMaxPasswordLength(int maxPasswordLength) {
        this.maxPasswordLength = maxPasswordLength;
    }

    public int getMinAboutLength() {
        return minAboutLength;
    }

    public void setMinAboutLength(int minAboutLength) {
        this.minAboutLength = minAboutLength;
    }

    public int getMaxAboutLength() {
        return maxAboutLength;
    }

    public void setMaxAboutLength(int maxAboutLength) {
        this.maxAboutLength = maxAboutLength;
    }

    public int getMinHomeCountryLength() {
        return minHomeCountryLength;
    }

    public void setMinHomeCountryLength(int minHomeCountryLength) {
        this.minHomeCountryLength = minHomeCountryLength;
    }

    public int getMaxHomeCountryLength() {
        return maxHomeCountryLength;
    }

    public void setMaxHomeCountryLength(int maxHomeCountryLength) {
        this.maxHomeCountryLength = maxHomeCountryLength;
    }

    public int getMinHomeCityLength() {
        return minHomeCityLength;
    }

    public void setMinHomeCityLength(int minHomeCityLength) {
        this.minHomeCityLength = minHomeCityLength;
    }

    public int getMaxHomeCityLength() {
        return maxHomeCityLength;
    }

    public void setMaxHomeCityLength(int maxHomeCityLength) {
        this.maxHomeCityLength = maxHomeCityLength;
    }
    
    
}
