package com.uade.tpo.Marketplace.service.invoices;

import com.uade.tpo.Marketplace.entity.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    List<Invoice> getInvoices();
    Optional<Invoice> getInvoiceById(Long id);
    Invoice createInvoice(float totalAmount, int dniUser, int receiptNumber, String type, Long orderId, Long typeReceiptId);
    Invoice updateInvoice(Long id, float totalAmount, int dniUser, int receiptNumber, String type, Long orderId, Long typeReceiptId);
    void deleteInvoice(Long id);
}


