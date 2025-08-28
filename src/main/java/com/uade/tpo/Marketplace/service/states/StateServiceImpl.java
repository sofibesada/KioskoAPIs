package com.uade.tpo.Marketplace.service.states;

import java.util.List;
import java.util.Optional;

import javax.management.loading.ClassLoaderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.Marketplace.entity.Country;
import com.uade.tpo.Marketplace.entity.State;
import com.uade.tpo.Marketplace.repository.country.CountryRespository;
import com.uade.tpo.Marketplace.repository.states.StateRepository;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRespository countryRepository;

    @Override
    public List<State> getStates() { return stateRepository.findAll(); }

    @Override
    public Optional<State> getStateById(Long id) { return stateRepository.findById(id); }

    @Override
    public State createState(String name, Long countryId) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new IllegalArgumentException("País no encontrado con id: " + countryId));

        State s = new State();
        s.setName(name);
        s.setCountry(country);
        return stateRepository.save(s);
    }

    @Override
    public State updateState(Long id, String name, Long countryId) {
        State s = stateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado con id: " + id));

        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new IllegalArgumentException("País no encontrado con id: " + countryId));

        s.setName(name);
        s.setCountry(country);
    return stateRepository.save(s);
}


    @Override
    public void deleteState(Long id) {
        if (!stateRepository.existsById(id)) {
            throw new IllegalArgumentException("Estado no encontrado con id: " + id);
        }
        stateRepository.deleteById(id);
    }
}

