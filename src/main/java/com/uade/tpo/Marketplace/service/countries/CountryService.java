package com.uade.tpo.Marketplace.service.countries;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.Marketplace.entity.Country;

public interface CountryService {
    List<Country> getCountries();
    Optional<Country> getCountryById(Long id);
    Country createCountry(String name);
    Country updateCountry(Long id, String name);
    void deleteCountry(Long id);
}
