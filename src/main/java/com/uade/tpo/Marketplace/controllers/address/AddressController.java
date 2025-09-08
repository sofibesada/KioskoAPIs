package com.uade.tpo.Marketplace.controllers.address;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uade.tpo.Marketplace.entity.Address;
import com.uade.tpo.Marketplace.service.address.AddressService;

@RestController
@RequestMapping("addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAddresses() {
        return ResponseEntity.ok(addressService.getAddresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<Object> createAddress(@RequestBody AddressRequest request) {
        Address result = addressService.createAddress(
                request.getStreet(),
                request.getNumber(),
                request.getFloor(),
                request.getDepartment(),
                request.getCodigoPostal(),
                request.getProvincia(),
                request.getCity()
                
        );
        return ResponseEntity.created(URI.create("/addresses/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody AddressRequest request) {
        Address updated = addressService.updateAddress(
                id,
                request.getStreet(),
                request.getNumber(),
                request.getFloor(),
                request.getDepartment(),
                request.getCodigoPostal(),
                request.getProvincia(),
                request.getCity()
                
        );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}