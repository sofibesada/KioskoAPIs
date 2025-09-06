package com.uade.tpo.Marketplace.controllers.typereceipts;


import com.uade.tpo.Marketplace.entity.TypeReceipt;
import com.uade.tpo.Marketplace.service.typereicepts.TypeReceiptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typereceipts")
public class TypeReceiptController {

    @Autowired
    private TypeReceiptService typeReceiptService;

    @GetMapping
    public List<TypeReceipt> getAllTypeReceipts() {
        return typeReceiptService.getTypeReceipts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeReceipt> getTypeReceiptById(@PathVariable Long id) {
        return typeReceiptService.getTypeReceiptById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TypeReceipt createTypeReceipt(@RequestBody TypeReceiptRequest request) {
        return typeReceiptService.createTypeReceipt(request.getNumber(), request.getReceipt());
    }

    @PutMapping("/{id}")
    public TypeReceipt updateTypeReceipt(@PathVariable Long id, @RequestBody TypeReceiptRequest request) {
        return typeReceiptService.updateTypeReceipt(id, request.getNumber(), request.getReceipt());
    }

    @DeleteMapping("/{id}")
    public void deleteTypeReceipt(@PathVariable Long id) {
        typeReceiptService.deleteTypeReceipt(id);
    }
}
