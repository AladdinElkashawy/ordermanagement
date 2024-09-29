package com.example.ordermanagement.error.exeption;

import lombok.Getter;

import java.util.NoSuchElementException;

@Getter
public class EmptyProductsException extends NoSuchElementException {
    private final int errorCode = 10001;
    private final String errorMessage = "EmptyProductsException";
    private final String description;

    public EmptyProductsException(String description) {
        this.description = description;
    }


}
