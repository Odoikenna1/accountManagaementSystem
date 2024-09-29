package com.semicolon.africa.data.repositories;

import com.semicolon.africa.data.models.BookKeeping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookKeepingRepo extends JpaRepository<BookKeeping, Long>{}
