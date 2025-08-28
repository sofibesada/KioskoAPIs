package com.uade.tpo.Marketplace.service.genders;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uade.tpo.Marketplace.entity.Genders;
import com.uade.tpo.Marketplace.repository.genders.GenderRepository;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderRepository genderRepository;

    @Override
    public List<Genders> getGenders() {
        return genderRepository.findAll();
    }

    @Override
    public Optional<Genders> getGenderById(Long id) {
        return genderRepository.findById(id);
    }

    @Override
    public Genders createGender(String gender) {
        Genders g = new Genders();
        g.setGender(gender);
        return genderRepository.save(g);
    }

    @Override
    public Genders updateGender(Long id, String gender) {
        Genders g = genderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Género no encontrado con id: " + id));

        g.setGender(gender);
        return genderRepository.save(g);
    }

    @Override
    public void deleteGender(Long id) {
        if (!genderRepository.existsById(id)) {
            throw new IllegalArgumentException("Género no encontrado con id: " + id);
        }
        genderRepository.deleteById(id);
    }
}
