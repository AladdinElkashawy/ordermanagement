package com.example.ordermanagement.error.exeption;

import lombok.Getter;

import java.util.NoSuchElementException;

@Getter
public class ProductDuplicateException extends NoSuchElementException {
    private final int errorCode = 10005;
    private final String errorMessage = "ProductDuplicateException";
    private final String description;

    public ProductDuplicateException(String description) {
        this.description = description;
    }


}
