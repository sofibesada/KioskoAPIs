package com.uade.tpo.Marketplace.repository.country;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.Marketplace.entity.Country;

@Repository
public interface CountryRespository extends JpaRepository<Country, Long>{
    List<Country> findByName(String name); 
}
