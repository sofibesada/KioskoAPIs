package com.uade.tpo.Marketplace.service.invoices;

import com.uade.tpo.Marketplace.entity.Invoice;
import com.uade.tpo.Marketplace.entity.Order;
import com.uade.tpo.Marketplace.entity.TypeReceipt;
import com.uade.tpo.Marketplace.repository.invoices.InvoiceRepository;
import com.uade.tpo.Marketplace.repository.orders.OrderRepository;
import com.uade.tpo.Marketplace.repository.typereceipts.TypeReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TypeReceiptRepository typeReceiptRepository;

    @Override
    public List<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public Invoice createInvoice(float totalAmount, int dniUser, int receiptNumber, String type, Long orderId, Long typeReceiptId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada con id: " + orderId));

        TypeReceipt typeReceipt = typeReceiptRepository.findById(typeReceiptId)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de comprobante no encontrado con id: " + typeReceiptId));

        Invoice invoice = new Invoice();
        invoice.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        invoice.setTotal_amount(totalAmount);
        invoice.setDniUser(dniUser);
        invoice.setReceiptNumber(receiptNumber);
        invoice.setType(type);
        invoice.setOrder(order);
        invoice.setTypeReceipt(typeReceipt);

        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice updateInvoice(Long id, float totalAmount, int dniUser, int receiptNumber, String type, Long orderId, Long typeReceiptId) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada con id: " + id));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada con id: " + orderId));

        TypeReceipt typeReceipt = typeReceiptRepository.findById(typeReceiptId)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de comprobante no encontrado con id: " + typeReceiptId));

        invoice.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        invoice.setTotal_amount(totalAmount);
        invoice.setDniUser(dniUser);
        invoice.setReceiptNumber(receiptNumber);
        invoice.setType(type);
        invoice.setOrder(order);
        invoice.setTypeReceipt(typeReceipt);

        return invoiceRepository.save(invoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}


