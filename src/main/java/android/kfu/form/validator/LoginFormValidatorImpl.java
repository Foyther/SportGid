/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.form.validator;

import android.kfu.form.LoginForm;
import org.springframework.stereotype.Service;

@Service
public class LoginFormValidatorImpl implements LoginFormValidator{

    @Override
    public int validate(LoginForm loginForm) {
        if (loginForm == null) {
            return ERROR_LOGIN_OR_PASSWORD_INCORRECT;
        }
        if(loginForm.getEmail().length() == 0 || loginForm.getPassword().length() == 0){
            return ERROR_LOGIN_OR_PASSWORD_IS_EMPTY;
        }
        return CODE_OK;
    }
    
    
    
}
