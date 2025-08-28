package com.uade.tpo.Marketplace.repository.typereceipts;


import com.uade.tpo.Marketplace.entity.TypeReceipt;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeReceiptRepository extends JpaRepository<TypeReceipt, Long> {
    Optional<TypeReceipt> findByNumber(int number);
   
    List<TypeReceipt> findByReceipt(String receipt);

 
    List<TypeReceipt> findByReceiptContainingIgnoreCase(String text);


    @Query("SELECT tr FROM TypeReceipt tr WHERE tr.number > :minNumber")
    List<TypeReceipt> findByNumberGreaterThan(int minNumber);
}

