package com.uade.tpo.Marketplace.repository.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.Marketplace.entity.City;


@Repository
public interface CityRepository extends JpaRepository<City, String> {
    
}
