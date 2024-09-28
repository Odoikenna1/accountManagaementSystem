package com.semicolon.africa.data.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookKeeping {

    @Id
    @GeneratedValue
    private Long id;
}
