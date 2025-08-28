package com.uade.tpo.Marketplace.controllers.categories.usertypes;



import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.uade.tpo.Marketplace.entity.UserType;
import com.uade.tpo.Marketplace.service.usertypes.UserTypeService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("usertypes")
public class UserTypeController {

    @Autowired
    private UserTypeService userTypeService;

    @GetMapping
    public ResponseEntity<List<UserType>> getUserTypes() {
        return ResponseEntity.ok(userTypeService.getUserTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserType> getUserTypeById(@PathVariable Long id) {
        return userTypeService.getUserTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<Object> createUserType(@RequestBody UserTypeRequest request) {
        UserType result = userTypeService.createUserType(request.getTypeUser());
        return ResponseEntity.created(URI.create("/usertypes/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserType> updateUserType(@PathVariable Long id, @RequestBody UserTypeRequest request) {
        UserType updated = userTypeService.updateUserType(id, request.getTypeUser());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserType(@PathVariable Long id) {
        userTypeService.deleteUserType(id);
        return ResponseEntity.noContent().build();
    }
}

