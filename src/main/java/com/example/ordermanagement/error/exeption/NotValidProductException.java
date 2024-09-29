package com.example.ordermanagement.error.exeption;

import lombok.Getter;

@Getter
public class  NotValidProductException extends IllegalArgumentException {
    private final int errorCode = 10003;
    private final String errorMessage = "Not Valid Product Exception";
    private final String description;

    public NotValidProductException(String description) {
        this.description = description;
    }

}
