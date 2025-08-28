package com.uade.tpo.Marketplace.service.typereicepts;

import com.uade.tpo.Marketplace.entity.TypeReceipt;

import java.util.List;
import java.util.Optional;

public interface TypeReceiptService {
    List<TypeReceipt> getTypeReceipts();
    Optional<TypeReceipt> getTypeReceiptById(Long id);
    TypeReceipt createTypeReceipt(int number, String receipt);
    TypeReceipt updateTypeReceipt(Long id, int number, String receipt);
    void deleteTypeReceipt(Long id);
}
