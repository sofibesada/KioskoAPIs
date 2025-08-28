package com.uade.tpo.Marketplace.repository.genders;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.uade.tpo.Marketplace.entity.Genders;

@Repository
public interface GenderRepository extends JpaRepository<Genders, Long> {

    @Query("SELECT g FROM Genders g WHERE g.gender = ?1")
    List<Genders> findByGender(String gender);

    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN true ELSE false END FROM Genders g WHERE g.gender = ?1")
    boolean existsByGender(String gender);
}

