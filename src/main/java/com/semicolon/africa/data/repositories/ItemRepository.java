package com.semicolon.africa.data.repositories;

import com.semicolon.africa.data.models.Item;
import com.semicolon.africa.data.type.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean getItemsByName(String name);

    Optional<Item> getItemsByNameAndCategory(String name, CategoryType category);

    boolean getItemsByCategory(CategoryType category);
}
