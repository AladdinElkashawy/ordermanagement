package com.example.ordermanagement.error.exeption;

import lombok.Getter;

import java.util.NoSuchElementException;

@Getter
public class OrderNotFoundException extends NoSuchElementException {
    private final int errorCode = 10004;
    private final String errorMessage = "OrderNotFound";
    private final String description;

    public OrderNotFoundException(String description) {
        this.description = description;
    }


}
