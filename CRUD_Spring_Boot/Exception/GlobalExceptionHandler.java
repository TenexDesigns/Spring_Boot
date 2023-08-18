package com.tenex.rest.springboot4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException exd) {
        // Log the error details for debugging purposes
        // You can use any logging framework here

        // Return a response entity with the error message and HTTP status code
        return new ResponseEntity<>(exd.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
