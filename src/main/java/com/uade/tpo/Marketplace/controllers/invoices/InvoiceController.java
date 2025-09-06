package com.uade.tpo.Marketplace.controllers.invoices;

import com.uade.tpo.Marketplace.entity.Invoice;
import com.uade.tpo.Marketplace.service.invoices.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public List<Invoice> getInvoices() {
        return invoiceService.getInvoices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceRequest request) {
        Invoice invoice = invoiceService.createInvoice(
                request.getTotalAmount(),
                request.getDniUser(),
                request.getReceiptNumber(),
                request.getType(),
                request.getOrderId(),
                request.getTypeReceiptId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(invoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody InvoiceRequest request) {
        Invoice invoice = invoiceService.updateInvoice(
                id,
                request.getTotalAmount(),
                request.getDniUser(),
                request.getReceiptNumber(),
                request.getType(),
                request.getOrderId(),
                request.getTypeReceiptId()
        );
        return ResponseEntity.ok(invoice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}
