package com.example.ordermanagement.error.exeption;

import lombok.Getter;

import java.util.NoSuchElementException;

@Getter
public class InsufficientStockException extends NoSuchElementException {
    private final int errorCode = 10002;
    private final String errorMessage = "InsufficientStockException";
    private final String description;

    public InsufficientStockException(String description) {
        this.description = description;
    }


}
