/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.form.validator;

import android.kfu.form.RegistrationForm;

/**
 *
 * @author rtmss
 */
public interface RegistrationFormValidator {
    
    boolean validate(RegistrationForm registrationForm);
    
}
