package com.semicolon.africa.controllers;


import com.semicolon.africa.dtos.TransactionDTO;
import com.semicolon.africa.services.ServiceImplementations.TransactionServicesImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionServicesImpl transactionServices;

    public TransactionController(TransactionServicesImpl transactionServices) {
        this.transactionServices = transactionServices;
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionServices.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO createdTransaction = transactionServices.createTransaction(transactionDTO);
        return ResponseEntity.status(201).body(createdTransaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        try {
            TransactionDTO transactionDTO = transactionServices.getTransactionById(id);
            return ResponseEntity.ok(transactionDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<TransactionDTO>> getTransactionByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<TransactionDTO> transactions = transactionServices.getTransactionByDate(localDate);
        return ResponseEntity.ok(transactions);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        try {
            transactionServices.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
