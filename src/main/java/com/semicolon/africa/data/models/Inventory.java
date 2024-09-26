package com.semicolon.africa.data.models;

import com.semicolon.africa.data.type.CategoryType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Inventory {
    @Id
    private Long id;
    private CategoryType category;
    private Long quantity;
    private double unitPrice;
    private LocalDateTime createdAt;
    private Long userId;
}
