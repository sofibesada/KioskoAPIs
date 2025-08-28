package com.uade.tpo.Marketplace.service.categories;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uade.tpo.Marketplace.entity.Category;
import com.uade.tpo.Marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.Marketplace.repository.categories.CategoryRepositoryNew;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepositoryNew categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Category createCategory(String name, String description) throws CategoryDuplicateException {
        if (categoryRepository.existsByName(name)) {
            throw new CategoryDuplicateException();
        }
        Category category = new Category(name);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, String name, String description) throws CategoryDuplicateException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con id: " + id));

        if (!category.getName().equals(name) && categoryRepository.existsByName(name)) {
            throw new CategoryDuplicateException();
        }

        category.setName(name);
        category.setDescription(description);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Categoría no encontrada con id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
