package com.uade.tpo.Marketplace.service.favorite;

import com.uade.tpo.Marketplace.entity.Favorite;

import java.util.List;
import java.util.Optional;

public interface FavoriteService {
    List<Favorite> getFavorites();
    Optional<Favorite> getFavoriteById(Long id);
    List<Favorite> getFavoritesByUser(Long userId);
    Favorite addFavorite(Long userId, Long productId);
    void deleteFavorite(Long id);
}

