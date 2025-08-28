package com.uade.tpo.Marketplace.service.users;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uade.tpo.Marketplace.entity.*;
import com.uade.tpo.Marketplace.exceptions.UserDuplicateException;
import com.uade.tpo.Marketplace.repository.users.UserRepository;
import com.uade.tpo.Marketplace.repository.address.AddressRepository;
import com.uade.tpo.Marketplace.repository.genders.GenderRepository;
import com.uade.tpo.Marketplace.repository.usertypes.UserTypeRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private AddressRepository addressRepository;
    @Autowired private GenderRepository genderRepository;
    @Autowired private UserTypeRepository userTypeRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findActiveUsers();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(String name, String surname, int dni, String email, String password, String phone,
                           java.time.LocalDate birthDate, Long addressId, Long genderId, Long userTypeId) throws UserDuplicateException {
        if (userRepository.existsByEmail(email)) {
            throw new UserDuplicateException();
        }

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Dirección no encontrada con id: " + addressId));
        Genders gender = genderRepository.findById(genderId)
                .orElseThrow(() -> new IllegalArgumentException("Género no encontrado con id: " + genderId));
        UserType userType = userTypeRepository.findById(userTypeId)
                .orElseThrow(() -> new IllegalArgumentException("UserType no encontrado con id: " + userTypeId));

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setDni(dni);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setBirthDate(birthDate);
        user.setCreatedAt(LocalDateTime.now());
        user.setAddress(address);
        user.setGender(gender);
        user.setUserType(userType);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, String name, String surname, int dni, String email, String password, String phone,
                           java.time.LocalDate birthDate, Long addressId, Long genderId, Long userTypeId) throws UserDuplicateException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + id));

        if (!user.getEmail().equals(email) && userRepository.existsByEmail(email)) {
            throw new UserDuplicateException();
        }

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Dirección no encontrada con id: " + addressId));
        Genders gender = genderRepository.findById(genderId)
                .orElseThrow(() -> new IllegalArgumentException("Género no encontrado con id: " + genderId));
        UserType userType = userTypeRepository.findById(userTypeId)
                .orElseThrow(() -> new IllegalArgumentException("UserType no encontrado con id: " + userTypeId));

        user.setName(name);
        user.setSurname(surname);
        user.setDni(dni);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setBirthDate(birthDate);
        user.setUpdatedAt(LocalDateTime.now());
        user.setAddress(address);
        user.setGender(gender);
        user.setUserType(userType);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + id));
        user.setDeleteAt(LocalDateTime.now()); // baja lógica
        userRepository.save(user);
    }
}

