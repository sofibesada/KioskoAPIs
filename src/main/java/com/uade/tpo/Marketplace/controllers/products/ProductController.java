package com.uade.tpo.Marketplace.controllers.products;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import com.uade.tpo.Marketplace.entity.Product;
import com.uade.tpo.Marketplace.exceptions.ProductDuplicateException;
import com.uade.tpo.Marketplace.service.products.ProductService;

import java.io.IOException;
import java.util.Base64; 
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ProductRequest request) throws ProductDuplicateException {
        Product result = productService.createProduct(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getStock(),
                request.getCategoryId()
        );
        return ResponseEntity.created(URI.create("/products/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) throws ProductDuplicateException {
        Product updated = productService.updateProduct(
                id,
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getStock(),
                request.getCategoryId()
        );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}/upload-image")
    public ResponseEntity<?> uploadImage(@PathVariable Long id,
                                     @RequestParam("image") MultipartFile file) throws IOException {
    Product product = productService.getProductById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    product.setImage(file.getBytes());
    productService.save(product);
    return ResponseEntity.ok("Imagen cargada correctamente");
    }
    @GetMapping("/{id}/image")
    public ResponseEntity<String> getImage(@PathVariable Long id) {
    Product product = productService.getProductById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    if (product.getImage() == null) {
        return ResponseEntity.noContent().build();
    }
    String base64 = Base64.getEncoder().encodeToString(product.getImage());
    return ResponseEntity.ok(base64);
    }


    @GetMapping("/available")
    public ResponseEntity<List<Product>> getAvailableProducts() {
        return ResponseEntity.ok(productService.getAvailableProducts());
    }

    @PatchMapping("/{id}/stock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateStock(
            @PathVariable Long id,
            @RequestParam int stock) {
        Product updated = productService.updateStock(id, stock);
        return ResponseEntity.ok(updated);
    }




}


