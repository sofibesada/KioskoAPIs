package com.uade.tpo.Marketplace.service.products;

import java.util.List;
import java.util.Optional;
import com.uade.tpo.Marketplace.entity.Product;
import com.uade.tpo.Marketplace.exceptions.ProductDuplicateException;

public interface ProductService {
    List<Product> getProducts();
    Optional<Product> getProductById(Long productId);
    Product createProduct(String name, String description, float price, int stock, Long categoryId) throws ProductDuplicateException;
    Product updateProduct(Long id, String name, String description, float price, int stock, Long categoryId) throws ProductDuplicateException;
    void deleteProduct(Long id);
    Product save(Product product);
    List<Product> getAvailableProducts();
}
