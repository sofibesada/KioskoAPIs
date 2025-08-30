// UserTypeRepository.java
package com.uade.tpo.Marketplace.repository.usertypes;

import com.uade.tpo.Marketplace.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
  Optional<UserType> findByTypeUser(String typeUser);
}


