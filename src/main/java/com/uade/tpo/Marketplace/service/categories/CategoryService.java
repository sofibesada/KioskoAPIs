package com.uade.tpo.Marketplace.service.categories;

import java.util.List;
import java.util.Optional;
import com.uade.tpo.Marketplace.entity.Category;
import com.uade.tpo.Marketplace.exceptions.CategoryDuplicateException;

public interface CategoryService {
    List<Category> getCategories();
    Optional<Category> getCategoryById(Long categoryId);
    Category createCategory(String name, String description) throws CategoryDuplicateException;
    Category updateCategory(Long id, String name, String description) throws CategoryDuplicateException;
    void deleteCategory(Long id);
}

