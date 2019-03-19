package com.gjm.shelterbrainbackend.security;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

public class GenericShelterControllerAdviceTest {
    @Test
    public void genericShelterBrainExceptionHandler() {
        GenericShelterControllerAdvice genericShelterControllerAdvice = new GenericShelterControllerAdvice();

        ResponseEntity responseEntity = genericShelterControllerAdvice.genericShelterBrainExceptionHandler(new ShelterBrainException(400, "responseMessage"));

        assertEquals(400, responseEntity.getStatusCodeValue());
        assertEquals("responseMessage", responseEntity.getBody());
    }
}