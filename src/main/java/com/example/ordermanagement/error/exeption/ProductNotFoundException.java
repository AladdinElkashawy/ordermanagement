package com.example.ordermanagement.error.exeption;

import lombok.Getter;

import java.util.NoSuchElementException;

@Getter
public class ProductNotFoundException extends NoSuchElementException {
    private final int errorCode = 10006;
    private final String errorMessage = "ProductNotFound";
    private final String description;

    public ProductNotFoundException(String description) {
        this.description = description;
    }

}
