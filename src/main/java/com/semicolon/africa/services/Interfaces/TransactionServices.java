package com.semicolon.africa.services.Interfaces;

import com.semicolon.africa.data.models.Transaction;
import com.semicolon.africa.dtos.TransactionDTO;

import java.time.LocalDate;
import java.util.List;

public interface TransactionServices {
    List<TransactionDTO> getAllTransactions();
    TransactionDTO createTransaction(TransactionDTO transactionDTO);
    TransactionDTO getTransactionById(Long id);
    List<TransactionDTO> getTransactionByDate(LocalDate date);
    List<Transaction> findByTimestamp(LocalDate date);

    void deleteTransaction(Long id);
}
