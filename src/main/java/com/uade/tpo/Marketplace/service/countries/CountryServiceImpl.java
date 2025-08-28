package com.uade.tpo.Marketplace.service.countries;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.Marketplace.entity.Country;
import com.uade.tpo.Marketplace.repository.country.CountryRespository;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRespository countryRepository;

    @Override
    public List<Country> getCountries() { return countryRepository.findAll(); }

    @Override
    public Optional<Country> getCountryById(Long id) { return countryRepository.findById(id); }

    @Override
    public Country createCountry(String name) {
        Country c = new Country();
        c.setName(name);
        return countryRepository.save(c);
    }

    @Override
    public Country updateCountry(Long id, String name) {
        Country c = countryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("País no encontrado con id: " + id));
        c.setName(name);
        return countryRepository.save(c);
    }

    @Override
    public void deleteCountry(Long id) {
        if (!countryRepository.existsById(id)) {
            throw new IllegalArgumentException("País no encontrado con id: " + id);
        }
        countryRepository.deleteById(id);
    }
}

