package com.uade.tpo.Marketplace.service.address;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uade.tpo.Marketplace.entity.Address;
import com.uade.tpo.Marketplace.entity.Province;
import com.uade.tpo.Marketplace.repository.address.AddressRepository;
import com.uade.tpo.Marketplace.repository.province.ProvinceRepository;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProvinceRepository provinciaRepository;

    @Override
    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address createAddress(String street, int number, int floor, int department, int codigoPostal, String city, String provinceId) {
        
        Address address = new Address();
        address.setStreet(street);
        address.setNumber(number);
        address.setFloor(floor);
        address.setDepartment(department);
        address.setCodigoPostal(codigoPostal);
        address.setCity(city);
        Province provincia = provinciaRepository.findById(provinceId)
                .orElseThrow(() -> new RuntimeException("Provincia no encontrada"));
      

        address.setProvince(provincia);


        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Long id, String street, int number, int floor, int department, int codigoPostal, String city, String provinceId) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dirección no encontrada con id: " + id));

        Province provincia = provinciaRepository.findById(provinceId)
                .orElseThrow(() -> new RuntimeException("Provincia no encontrada"));
       

        address.setProvince(provincia);
        address.setCity(city);

        address.setStreet(street);
        address.setNumber(number);
        address.setFloor(floor);
        address.setDepartment(department);
        address.setCodigoPostal(codigoPostal);

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
