package com.semicolon.africa.services.ServiceImplementations;

import com.semicolon.africa.data.models.Transaction;
import com.semicolon.africa.data.repositories.TransactionRepository;
import com.semicolon.africa.dtos.TransactionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.semicolon.africa.data.type.PaymentMethod.*;
import static com.semicolon.africa.data.type.TransactionType.CREDIT;
import static com.semicolon.africa.data.type.TransactionType.DEBIT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServicesImplTest {

    private TransactionRepository transactionRepository;
    private TransactionServicesImpl transactionServices;

    @BeforeEach
    void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        transactionServices = new TransactionServicesImpl(transactionRepository);
    }
    @Test
    void getAllTransactions_WhenNoTransactions_ShouldReturnEmptyList() {
        when(transactionRepository.findAll()).thenReturn(List.of());
        List<TransactionDTO> result = transactionServices.getAllTransactions();
        assertTrue(result.isEmpty());
    }
    @Test
    void getAllTransactions_ShouldReturnAllTransactions() {
        // Given
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setTransactionType(CREDIT);
        transaction.setDescription("Test transaction");
        transaction.setAmount(100.00);
        transaction.setPaymentMethod(CASH);
        transaction.setTimestamp(LocalDateTime.now());

        when(transactionRepository.findAll()).thenReturn(List.of(transaction));

        // When
        List<TransactionDTO> result = transactionServices.getAllTransactions();

        // Then
        assertEquals(1, result.size());
        assertEquals(CREDIT, result.get(0).getTransactionType());
    }

    @Test
    void createTransaction_ShouldReturnCreatedTransaction() {
        // Given
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .transactionType(DEBIT)
                .description("Test transaction")
                .amount(100.0)
                .paymentMethod(BANK_TRANSFER)
                .timestamp(LocalDateTime.now())
                .build();

        Transaction savedTransaction = new Transaction();
        savedTransaction.setId(1L);
        savedTransaction.setTransactionType(transactionDTO.getTransactionType());
        savedTransaction.setDescription(transactionDTO.getDescription());
        savedTransaction.setAmount(transactionDTO.getAmount());
        savedTransaction.setPaymentMethod(transactionDTO.getPaymentMethod());
        savedTransaction.setTimestamp(transactionDTO.getTimestamp());

        when(transactionRepository.save(any(Transaction.class))).thenReturn(savedTransaction);

        // When
        TransactionDTO result = transactionServices.createTransaction(transactionDTO);

        // Then
        assertNotNull(result);
        assertEquals(DEBIT, result.getTransactionType());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void getTransactionById_ExistingId_ShouldReturnTransaction() {
        // Given
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setTransactionType(CREDIT);
        transaction.setDescription("Test transaction");
        transaction.setAmount(100.00);
        transaction.setPaymentMethod(USSD);
        transaction.setTimestamp(LocalDateTime.now());

        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        // When
        TransactionDTO result = transactionServices.getTransactionById(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(CREDIT, result.getTransactionType());
    }

    @Test
    void getTransactionById_NonExistingId_ShouldThrowEntityNotFoundException() {
        // Given
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> transactionServices.getTransactionById(1L));
    }

    @Test
    void getTransactionByDate_ShouldReturnTransactions() {
        // Given
        LocalDate date = LocalDate.now();
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setTransactionType(DEBIT);
        transaction.setDescription("Test transaction");
        transaction.setAmount(200.00);
        transaction.setPaymentMethod(BANK_TRANSFER);
        transaction.setTimestamp(date.atStartOfDay());

        when(transactionRepository.findByTimestamp(date)).thenReturn(List.of(transaction));

        // When
        List<TransactionDTO> result = transactionServices.getTransactionByDate(date);

        // Then
        assertEquals(1, result.size());
        assertEquals(DEBIT, result.get(0).getTransactionType());
    }

    @Test
    void deleteTransaction_ExistingId_ShouldDeleteTransaction() {
        // Given
        when(transactionRepository.existsById(1L)).thenReturn(true);

        // When
        transactionServices.deleteTransaction(1L);

        // Then
        verify(transactionRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteTransaction_NonExistingId_ShouldThrowEntityNotFoundException() {
        // Given
        when(transactionRepository.existsById(1L)).thenReturn(false);

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> transactionServices.deleteTransaction(1L));
    }
}
