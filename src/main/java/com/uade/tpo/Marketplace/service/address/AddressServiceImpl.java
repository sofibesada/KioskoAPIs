package com.uade.tpo.Marketplace.service.address;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uade.tpo.Marketplace.entity.Address;
import com.uade.tpo.Marketplace.entity.State;
import com.uade.tpo.Marketplace.repository.address.AddressRepository;
import com.uade.tpo.Marketplace.repository.states.StateRepository;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

      @Autowired
    private StateRepository stateRepository;

    @Override
    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address createAddress(String street, int number, int floor, int department, int codigoPostal, Long stateId) {
        State state = stateRepository.findById(stateId)
                .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado con id: " + stateId));

        Address address = new Address();
        address.setStreet(street);
        address.setNumber(number);
        address.setFloor(floor);
        address.setDepartment(department);
        address.setCodigoPostal(codigoPostal);
        address.setState(state);

        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Long id, String street, int number, int floor, int department, int codigoPostal, Long stateId) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dirección no encontrada con id: " + id));

        State state = stateRepository.findById(stateId)
                .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado con id: " + stateId));

        address.setStreet(street);
        address.setNumber(number);
        address.setFloor(floor);
        address.setDepartment(department);
        address.setCodigoPostal(codigoPostal);
        address.setState(state);

        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new IllegalArgumentException("Dirección no encontrada con id: " + id);
        }
        addressRepository.deleteById(id);
    }
}
