package com.semicolon.africa.data.models;

import com.semicolon.africa.data.type.CategoryType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Item {
    @Id
    private Long id;
    private String name;
    private CategoryType category;
    private Long stock;
    private Long inventoryId;
}
