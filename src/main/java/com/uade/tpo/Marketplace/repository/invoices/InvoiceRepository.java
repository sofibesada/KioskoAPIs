package com.uade.tpo.Marketplace.repository.invoices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.Marketplace.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByOrderId(Long orderId);

    List<Invoice> findyByDniUser(int dniUser);
    List<Invoice> findByType(String type);
    List<Invoice> findByReceiptNumber(int receiptNumber);
    
}

