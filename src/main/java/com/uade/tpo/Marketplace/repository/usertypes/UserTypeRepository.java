package com.uade.tpo.Marketplace.repository.usertypes;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.uade.tpo.Marketplace.entity.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {

    @Query("SELECT u FROM UserType u WHERE u.typeUser = ?1")
    List<UserType> findByTypeUser(String typeUser);
}

