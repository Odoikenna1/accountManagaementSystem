package com.semicolon.africa.services.ServiceImplementations;

import com.semicolon.africa.data.models.Transaction;
import com.semicolon.africa.data.repositories.TransactionRepository;
import com.semicolon.africa.dtos.TransactionDTO;
import com.semicolon.africa.services.Interfaces.TransactionServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TransactionServicesImpl implements TransactionServices {
    private static final Logger logger = LoggerFactory.getLogger(TransactionServicesImpl.class);
    private final TransactionRepository transactionRepository;

    public TransactionServicesImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionDTO.getTransactionType());
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setPaymentMethod(transactionDTO.getPaymentMethod());
        transaction.setTimestamp(transactionDTO.getTimestamp());

        Transaction savedTransaction = transactionRepository.save(transaction);
        logger.info("Transaction created with id: {}", savedTransaction.getId());

        return convertToDTO(savedTransaction);
    }

    @Override
    public TransactionDTO getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> {
                    logger.error("Transaction not found with id: {}", id);
                    return new EntityNotFoundException("Transaction not found with id: " + id);
                });
    }

    @Override
    public List<TransactionDTO> getTransactionByDate(LocalDate date) {
        List<Transaction> transactions = transactionRepository.findByTimestamp(date);
        return transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findByTimestamp(LocalDate date) {
        return transactionRepository.findByTimestamp(date); // Implement this in the repository
    }

    @Override
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            logger.error("Transaction not found with id: {}", id);
            throw new EntityNotFoundException("Transaction not found with id: " + id);
        }
        transactionRepository.deleteById(id);
        logger.info("Transaction with id: {} deleted", id);
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .transactionType(transaction.getTransactionType())
                .description(transaction.getDescription())
                .amount(transaction.getAmount().doubleValue())
                .paymentMethod(transaction.getPaymentMethod())
                .timestamp(transaction.getTimestamp())
                .build();
    }
}
