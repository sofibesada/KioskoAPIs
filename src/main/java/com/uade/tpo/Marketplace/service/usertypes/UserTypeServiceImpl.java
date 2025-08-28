package com.uade.tpo.Marketplace.service.usertypes;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uade.tpo.Marketplace.entity.UserType;
import com.uade.tpo.Marketplace.repository.usertypes.UserTypeRepository;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Override
    public List<UserType> getUserTypes() {
        return userTypeRepository.findAll();
    }

    @Override
    public Optional<UserType> getUserTypeById(Long id) {
        return userTypeRepository.findById(id);
    }

    @Override
    public UserType createUserType(String typeUser) {
        UserType userType = new UserType();
        userType.setTypeUser(typeUser);
        return userTypeRepository.save(userType);
    }

    @Override
    public UserType updateUserType(Long id, String typeUser) {
        UserType userType = userTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("UserType no encontrado con id: " + id));

        userType.setTypeUser(typeUser);
        return userTypeRepository.save(userType);
    }

    @Override
    public void deleteUserType(Long id) {
        if (!userTypeRepository.existsById(id)) {
            throw new IllegalArgumentException("UserType no encontrado con id: " + id);
        }
        userTypeRepository.deleteById(id);
    }
}

