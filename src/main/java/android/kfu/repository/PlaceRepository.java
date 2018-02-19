/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.repository;

import android.kfu.entities.KindOfSport;
import android.kfu.entities.Place;
import android.kfu.entities.User;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long>{
    @Query(value = "SELECT * from places\n" +
            "WHERE places.city = ?2 AND\n" +
            "      not exists(select places_kind_of_sports.places_id\n" +
            "      from places_kind_of_sports\n" +
            "WHERE NOT exists(\n" +
            "      (SELECT kind_of_sports.id FROM kind_of_sports))",nativeQuery = true)
    Set<Place> findAllOrderedByRating(KindOfSport sport, String city);

    Set<Place> findAllBySportAndCity(KindOfSport sport, String city);

    Set<Place> findAllByCityAndSport(KindOfSport sport, String city);

}
