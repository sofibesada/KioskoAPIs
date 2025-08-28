package com.uade.tpo.Marketplace.repository.address;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.uade.tpo.Marketplace.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.codigoPostal = ?1")
    List<Address> findByCodigoPostal(int codigoPostal);
}

