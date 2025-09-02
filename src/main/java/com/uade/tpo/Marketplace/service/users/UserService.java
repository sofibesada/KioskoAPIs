package com.uade.tpo.Marketplace.service.users;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.Marketplace.controllers.categories.users.UserRequest;

import com.uade.tpo.Marketplace.entity.User;

import com.uade.tpo.Marketplace.exceptions.UserDuplicateException;

public interface UserService {
    List<User> getUsers();
    Optional<User> getUserById(Long id);
    User updateUser(Long id, UserRequest request) throws UserDuplicateException;
    void deleteUser(Long id);
}
