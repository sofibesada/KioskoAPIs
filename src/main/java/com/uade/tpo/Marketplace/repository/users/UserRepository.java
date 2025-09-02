package com.uade.tpo.Marketplace.repository.users;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.Marketplace.entity.User;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = ?1")
    boolean existsByEmail(String email);


    @Query("SELECT u FROM User u WHERE u.name = ?1 AND u.surname = ?2")
    List<User> findByNameAndSurname(String name, String surname);

    @Query("SELECT u FROM User u WHERE u.deleteAt IS NULL")
    List<User> findActiveUsers();

   
}