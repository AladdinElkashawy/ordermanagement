package com.example.ordermanagement.error.exeption;



import com.example.ordermanagement.error.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.ordermanagement.timing.TimeUtils.getCurentTimeStamp;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Response> handleProductNotFoundException(ProductNotFoundException exception) {
        Response errorResponse = new Response(exception.getErrorCode(),
                exception.getErrorMessage(),
                exception.getDescription(),
                getCurentTimeStamp());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Response> handleOrderNotFoundException(OrderNotFoundException exception) {
        Response errorResponse = new Response(exception.getErrorCode(),
                exception.getErrorMessage(),
                exception.getDescription(),
                getCurentTimeStamp());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotValidProductException.class)
    public ResponseEntity<Response> handleProductNotValidException(NotValidProductException exception) {
        Response errorResponse = new Response(exception.getErrorCode(),
                exception.getErrorMessage(),
                exception.getDescription(),
                getCurentTimeStamp());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EmptyProductsException.class)
    public ResponseEntity<Response> handleEmptyProductsException(EmptyProductsException exception) {
        Response errorResponse = new Response(exception.getErrorCode(),
                exception.getErrorMessage(),
                exception.getDescription(),
                getCurentTimeStamp());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Response> handleInsufficientStockException(InsufficientStockException exception) {
        Response errorResponse = new Response(exception.getErrorCode(),
                exception.getErrorMessage(),
                exception.getDescription(),
                getCurentTimeStamp());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ProductDuplicateException.class)
    public ResponseEntity<Response> handleProductDuplicateException(ProductDuplicateException exception) {
        Response errorResponse = new Response(exception.getErrorCode(),
                exception.getErrorMessage(),
                exception.getDescription(),
                getCurentTimeStamp());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }
}
