package com.uade.tpo.Marketplace.service.usertypes;

import java.util.List;
import java.util.Optional;
import com.uade.tpo.Marketplace.entity.UserType;

public interface UserTypeService {
    List<UserType> getUserTypes();
    Optional<UserType> getUserTypeById(Long id);
    UserType createUserType(String typeUser);
    UserType updateUserType(Long id, String typeUser);
    void deleteUserType(Long id);
}
