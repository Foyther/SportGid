/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.form.validator;

import android.kfu.form.LoginForm;

/**
 *
 * @author rtmss
 */
public interface LoginFormValidator {
 
    int CODE_OK = 0;
    
    int ERROR_LOGIN_OR_PASSWORD_INCORRECT = 1;

    int ERROR_LOGIN_OR_PASSWORD_IS_EMPTY = 2;

    int validate(LoginForm loginForm);
    
}
