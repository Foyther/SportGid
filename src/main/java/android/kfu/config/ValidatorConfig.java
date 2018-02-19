/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.config;

import android.kfu.form.validator.EditProfileFormValidator;
import android.kfu.form.validator.EditProfileFormValidatorImpl;
import android.kfu.form.validator.RegistrationFormValidator;
import android.kfu.form.validator.RegistrationFormValidatorImpl;
import android.kfu.tools.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"android.kfu.form.validator"})
public class ValidatorConfig {
    
    @Autowired
    @Bean
    public RegistrationFormValidator registrationFormValidator(EmailValidator emailValidator) {
        return new RegistrationFormValidatorImpl(1, 128, 5, 256, 6, 256, emailValidator);
    }
    
    @Autowired
    @Bean
    public EditProfileFormValidator editProfileFormValidator(EmailValidator emailValidator) {
        return new EditProfileFormValidatorImpl(1, 64, 5, 256, 6, 256, 0, 1024, 0, 64, 0, 64, emailValidator);
    }
}
