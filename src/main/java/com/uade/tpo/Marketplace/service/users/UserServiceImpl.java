package com.uade.tpo.Marketplace.service.users;

import com.uade.tpo.Marketplace.controllers.users.UserRequest;
import com.uade.tpo.Marketplace.entity.User;
import com.uade.tpo.Marketplace.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Para encriptar passwords

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    

    @Override
    public User updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + id));

        if (request.getName() != null) user.setName(request.getName());
        if (request.getSurname() != null) user.setSurname(request.getSurname());
        if (request.getEmail() != null) user.setEmail(request.getEmail());

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getPhone() != null) user.setPhone(request.getPhone());
        if (request.getBirthDate() != null) user.setBirthDate(request.getBirthDate());
        if (request.getAddressId() != null) {
            //user.setAddress(AddressRepository.findById(request.getAddressId())
              //      .orElseThrow(() -> new IllegalArgumentException("Dirección no encontrada")));
        }

        if (request.getUsertype() != null) user.setUserType(request.getUsertype());
        if (request.getGender() != null) user.setGender(request.getGender());

        return userRepository.save(user);
    }


    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado con id: " + id);
        }
        userRepository.deleteById(id);
    }
}


