package android.kfu.repository;

import android.kfu.entities.KindOfSport;
import android.kfu.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Nurislam on 17.12.2017.
 */
@Repository
public interface KindOfSportRepository extends JpaRepository<KindOfSport, Long> {}
