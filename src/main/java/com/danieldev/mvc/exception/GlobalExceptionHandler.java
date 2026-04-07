package com.danieldev.mvc.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handle(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}