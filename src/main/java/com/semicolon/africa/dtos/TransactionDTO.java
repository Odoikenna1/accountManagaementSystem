package com.semicolon.africa.dtos;

import com.semicolon.africa.data.type.PaymentMethod;
import com.semicolon.africa.data.type.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class TransactionDTO {
    private Long id;
    private TransactionType transactionType;
    private String description;
    private Double amount;
    private PaymentMethod paymentMethod;
    private LocalDateTime timestamp;
}
