package com.uade.tpo.Marketplace.service.states;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.Marketplace.entity.State;

public interface StateService {
    List<State> getStates();
    Optional<State> getStateById(Long id);
    State createState(String name, Long countryId);
    State updateState(Long id, String name, Long countryId);
    void deleteState(Long id);
}

