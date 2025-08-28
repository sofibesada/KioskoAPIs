package com.uade.tpo.Marketplace.controllers.categories.genders;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.Marketplace.entity.Genders;
import com.uade.tpo.Marketplace.service.genders.GenderService;

@RestController
@RequestMapping("genders")
public class GendersControllers {

    @Autowired
    private GenderService genderService;

    @GetMapping
    public ResponseEntity<List<Genders>> getGenders() {
        return ResponseEntity.ok(genderService.getGenders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genders> getGenderById(@PathVariable Long id) {
        return genderService.getGenderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<Object> createGender(@RequestBody GendersRequest request) {
        Genders result = genderService.createGender(request.getGender());
        return ResponseEntity.created(URI.create("/genders/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genders> updateGender(@PathVariable Long id, @RequestBody GendersRequest request) {
        Genders updated = genderService.updateGender(id, request.getGender());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGender(@PathVariable Long id) {
        genderService.deleteGender(id);
        return ResponseEntity.noContent().build();
    }
}

