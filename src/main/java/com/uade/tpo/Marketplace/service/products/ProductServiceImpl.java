package com.uade.tpo.Marketplace.service.products;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uade.tpo.Marketplace.entity.Category;
import com.uade.tpo.Marketplace.entity.Product;
import com.uade.tpo.Marketplace.exceptions.ProductDuplicateException;
import com.uade.tpo.Marketplace.repository.categories.CategoryRepositoryNew;
import com.uade.tpo.Marketplace.repository.products.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepositoryNew categoryRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product createProduct(String name, String description, float price, int stock, Long categoryId) throws ProductDuplicateException {
        if (productRepository.existsByName(name)) {
            throw new ProductDuplicateException();
        }

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con id: " + categoryId));

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, String name, String description, float price, int stock, Long categoryId) throws ProductDuplicateException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con id: " + id));

        if (!product.getName().equals(name) && productRepository.existsByName(name)) {
            throw new ProductDuplicateException();
        }

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con id: " + categoryId));

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Producto no encontrado con id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAvailableProducts() {
        return productRepository.findByStockGreaterThan(0);
    }


}
