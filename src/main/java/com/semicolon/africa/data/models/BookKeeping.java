package com.semicolon.africa.data.models;

import com.semicolon.africa.data.type.PaymentMethod;
import com.semicolon.africa.data.type.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class BookKeeping {

    @Id
    @GeneratedValue
    private Long id;

    private String receiverName;

    private String senderName;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}
