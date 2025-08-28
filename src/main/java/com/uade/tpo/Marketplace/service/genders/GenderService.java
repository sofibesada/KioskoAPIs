package com.uade.tpo.Marketplace.service.genders;

import java.util.List;
import java.util.Optional;
import com.uade.tpo.Marketplace.entity.Genders;


public interface GenderService {
    List<Genders> getGenders();
    Optional<Genders> getGenderById(Long id);
    Genders createGender(String gender);
    Genders updateGender(Long id, String gender);
    void deleteGender(Long id);
}

