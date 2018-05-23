/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.form.validator;

import android.kfu.form.RegistrationForm;
import android.kfu.tools.EmailValidator;
import android.kfu.tools.StringTool;

public class RegistrationFormValidatorImpl implements RegistrationFormValidator {


    private int minNameLength;

    private int maxNameLength;

    private int minEmailLength;

    private int maxEmailLength;

    private int minPasswordLength;

    private int maxPasswordLength;

    private EmailValidator emailValidator;

    public RegistrationFormValidatorImpl(int minNameLength, int maxNameLength,
                                         int minEmailLength, int maxEmailLength, int minPasswordLength,
                                         int maxPasswordLength, EmailValidator emailValidator) {
        this.minNameLength = minNameLength;
        this.maxNameLength = maxNameLength;
        this.minEmailLength = minEmailLength;
        this.maxEmailLength = maxEmailLength;
        this.minPasswordLength = minPasswordLength;
        this.maxPasswordLength = maxPasswordLength;
        this.emailValidator = emailValidator;
    }

    @Override
    public boolean validate(RegistrationForm registrationForm, boolean social) {
        if (registrationForm == null) {
            return false;
        } else {
            if (social) {
                return true;
            } else {
                return checkName(registrationForm.getName()) &&
                    checkEmail(registrationForm.getEmail()) &&
                        checkPassword(registrationForm.getPassword());
            }
        }
    }


    private boolean checkName(String name) {
        return StringTool.isInBounds(name, minNameLength, maxNameLength);
    }

    private boolean checkPassword(String password) {
        return StringTool.isInBounds(password, minPasswordLength, maxPasswordLength);
    }

    private boolean checkEmail(String email) {
        if (email == null) {
            return false;
        } else {
            if (!StringTool.isInBounds(email, minEmailLength, maxEmailLength)) {
                return false;
            }
        }

        return emailValidator.validate(email);
    }


}
