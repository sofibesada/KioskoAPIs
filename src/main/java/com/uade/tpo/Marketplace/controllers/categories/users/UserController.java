package com.uade.tpo.Marketplace.controllers.categories.users;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.uade.tpo.Marketplace.entity.User;
import com.uade.tpo.Marketplace.exceptions.UserDuplicateException;
import com.uade.tpo.Marketplace.service.users.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserRequest request) throws UserDuplicateException {
        User result = userService.createUser(
                request.getName(),
                request.getSurname(),
                request.getDni(),
                request.getEmail(),
                request.getPassword(),
                request.getPhone(),
                request.getBirthDate(),
                request.getAddressId(),
                request.getGenderId(),
                request.getUserTypeId()
        );
        return ResponseEntity.created(URI.create("/users/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserRequest request) throws UserDuplicateException {
        User updated = userService.updateUser(
                id,
                request.getName(),
                request.getSurname(),
                request.getDni(),
                request.getEmail(),
                request.getPassword(),
                request.getPhone(),
                request.getBirthDate(),
                request.getAddressId(),
                request.getGenderId(),
                request.getUserTypeId()
        );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
