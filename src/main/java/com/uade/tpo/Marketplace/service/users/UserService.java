package com.uade.tpo.Marketplace.service.users;

import java.util.List;
import java.util.Optional;
import com.uade.tpo.Marketplace.entity.User;
import com.uade.tpo.Marketplace.exceptions.UserDuplicateException;

public interface UserService {
    List<User> getUsers();
    Optional<User> getUserById(Long id);
    User createUser(String name, String surname, int dni, String email, String password, String phone, 
                    java.time.LocalDate birthDate, Long addressId, Long genderId, Long userTypeId) throws UserDuplicateException;
    User updateUser(Long id, String name, String surname, int dni, String email, String password, String phone, 
                    java.time.LocalDate birthDate, Long addressId, Long genderId, Long userTypeId) throws UserDuplicateException;
    void deleteUser(Long id);
}
