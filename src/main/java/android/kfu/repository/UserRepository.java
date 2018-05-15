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

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    User findOneByEmailAndPasswordCrypt(String email, String passwordCrypt);

    User findOneByEmail(String email);

    User findOneById(Long id);

    @Query(value = "SELECT * from users WHERE id = (select user_tokens.user_id from user_tokens WHERE access_token = ?1)",nativeQuery = true)
    User findOneByToken(String token);
}
