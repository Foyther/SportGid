/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.config;

import android.kfu.service.api.auth.AuthService;
import android.kfu.service.api.auth.AuthServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"android.kfu.service"})
public class ServiceConfig {

    @Bean
    public AuthService authService() {
        return new AuthServiceImpl(60 * 60 * 60 * 1000);
    }
}
