package com.uade.tpo.Marketplace.controllers.categories;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.uade.tpo.Marketplace.entity.Category;
import com.uade.tpo.Marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.Marketplace.service.categories.CategoryService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoriesControllers {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest request)
            throws CategoryDuplicateException {
        Category result = categoryService.createCategory(request.getName(), request.getDescription());
        return ResponseEntity.created(URI.create("/categories/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest request)
            throws CategoryDuplicateException {
        Category updated = categoryService.updateCategory(id, request.getName(), request.getDescription());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}

