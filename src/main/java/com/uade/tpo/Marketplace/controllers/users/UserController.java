package com.uade.tpo.Marketplace.controllers.users;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import com.uade.tpo.Marketplace.entity.User;
import com.uade.tpo.Marketplace.exceptions.UserDuplicateException;
import com.uade.tpo.Marketplace.service.users.UserService;

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
    @PreAuthorize("hasAuthority('ADMIN') or @userSecurity.isSelf(#id, authentication)")
    public ResponseEntity<User> getUser(@PathVariable Long id, Authentication auth) {
        return userService.getUserById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    



    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or @userSecurity.isSelf(#id, authentication)")
    public ResponseEntity<User> patchUser(@PathVariable Long id,
                                                @RequestBody UserRequest request,
                                                 Authentication auth) throws UserDuplicateException {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

  

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
