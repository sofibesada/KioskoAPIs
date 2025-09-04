package com.uade.tpo.Marketplace.service.favorite;

import com.uade.tpo.Marketplace.entity.Favorite;
import com.uade.tpo.Marketplace.entity.Product;
import com.uade.tpo.Marketplace.entity.User;
import com.uade.tpo.Marketplace.repository.favorite.FavoriteRepository;
import com.uade.tpo.Marketplace.repository.products.ProductRepository;
import com.uade.tpo.Marketplace.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Favorite> getFavorites() {
        return favoriteRepository.findAll();
    }

    @Override
    public Optional<Favorite> getFavoriteById(Long id) {
        return favoriteRepository.findById(id);
    }

    @Override
    public List<Favorite> getFavoritesByUser(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

    @Override
    public Favorite addFavorite(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con id: " + productId));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);

        return favoriteRepository.save(favorite);
    }

    @Override
    public void deleteFavorite(Long id) {
        favoriteRepository.deleteById(id);
    }
}

