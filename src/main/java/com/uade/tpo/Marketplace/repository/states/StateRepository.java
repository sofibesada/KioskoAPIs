package com.uade.tpo.Marketplace.repository.states;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.Marketplace.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    @Query("SELECT s FROM State s WHERE s.name = ?1")
    List<State> findByName(String name);
}

