package com.gjm.shelterbrainbackend.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericShelterControllerAdvice {
    @ExceptionHandler(ShelterBrainException.class)
    public ResponseEntity<?> genericShelterBrainExceptionHandler(ShelterBrainException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(exception.getResponseMessage());
    }
}
