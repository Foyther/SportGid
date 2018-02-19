/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.repository;

import android.kfu.entities.User;
import android.kfu.entities.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository  extends JpaRepository<UserToken, Long>{
    
    UserToken findOneByAccessToken(String accessToken);
    
    UserToken findOneByRefreshToken(String refreshToken);
}
