package com.uade.tpo.Marketplace.service.typereicepts;


import com.uade.tpo.Marketplace.entity.TypeReceipt;
import com.uade.tpo.Marketplace.repository.typereceipts.TypeReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeReceiptServiceImpl implements TypeReceiptService {

    @Autowired
    private TypeReceiptRepository typeReceiptRepository;

    @Override
    public List<TypeReceipt> getTypeReceipts() {
        return typeReceiptRepository.findAll();
    }

    @Override
    public Optional<TypeReceipt> getTypeReceiptById(Long id) {
        return typeReceiptRepository.findById(id);
    }

    @Override
    public TypeReceipt createTypeReceipt(int number, String receipt) {
        TypeReceipt tr = new TypeReceipt();
        tr.setNumber(number);
        tr.setReceipt(receipt);
        return typeReceiptRepository.save(tr);
    }

    @Override
    public TypeReceipt updateTypeReceipt(Long id, int number, String receipt) {
        TypeReceipt tr = typeReceiptRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de comprobante no encontrado con id: " + id));

        tr.setNumber(number);
        tr.setReceipt(receipt);

        return typeReceiptRepository.save(tr);
    }

    @Override
    public void deleteTypeReceipt(Long id) {
        typeReceiptRepository.deleteById(id);
    }
}

