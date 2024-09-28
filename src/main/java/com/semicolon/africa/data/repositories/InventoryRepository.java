package com.semicolon.africa.data.repositories;

import com.semicolon.africa.data.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
