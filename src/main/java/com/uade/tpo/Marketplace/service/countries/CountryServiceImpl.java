package com.uade.tpo.Marketplace.service.countries;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uade.tpo.Marketplace.controllers.categories.countries.CountryApiResponse;
import com.uade.tpo.Marketplace.entity.Country;
import com.uade.tpo.Marketplace.entity.State;
import com.uade.tpo.Marketplace.repository.country.CountryRespository;
import com.uade.tpo.Marketplace.repository.states.StateRepository;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRespository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private RestTemplate restTemplate;

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

    public void importCountriesFromApi() {
        String url = "https://countriesnow.space/api/v0.1/countries/states";

        CountryApiResponse response = restTemplate.getForObject(url, CountryApiResponse.class);

        if (response != null && !response.isError()) {
            for (CountryApiResponse.CountryData apiCountry : response.getData()) {
                Country country = new Country();
                country.setName(apiCountry.getName());

                List<State> states = apiCountry.getStates().stream().map(apiState -> {
                    State state = new State();
                    state.setName(apiState.getName());
                    state.setCountry(country);
                    return state;
                })
                .toList();

                country.setStates(states);
                countryRepository.save(country);
            }
        }
    }
}
