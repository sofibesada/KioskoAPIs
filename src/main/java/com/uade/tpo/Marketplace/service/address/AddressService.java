package com.uade.tpo.Marketplace.service.address;

import java.util.List;
import java.util.Optional;
import com.uade.tpo.Marketplace.entity.Address;

public interface AddressService {
    List<Address> getAddresses();
    Optional<Address> getAddressById(Long id);
    Address createAddress(String street, int number, int floor, int department, int codigoPostal, String city, String province);
    Address updateAddress(Long id, String street, int number, int floor, int department, int codigoPostal, String city, String province);
    void deleteAddress(Long id);

}
