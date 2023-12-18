package com.example.demo.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public ResponseEntity<CustomResponse<?>> handleNotFoundException(NoHandlerFoundException ex) {
        CustomResponse<String> response = new CustomResponse<>("Not Implemented", "Requested method is not implemented","null");
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
    }
}