package com.gjm.shelterbrainbackend.security;

import org.junit.Test;
import org.springframework.validation.FieldError;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class ShelterBeanValidationExceptionTest {
    @Test
    public void validationErrorsFormatting() {
        List<FieldError> validationErrors = new LinkedList<>();

        validationErrors.add(new FieldError("x", "y", "message1"));
        validationErrors.add(new FieldError("a", "b", "message2"));

        ShelterBeanValidationException shelterBeanValidationException = new ShelterBeanValidationException(validationErrors);

        assertEquals(422, shelterBeanValidationException.getHttpStatus());
        assertEquals("Błąd walidacji!\nmessage1\nmessage2\n", shelterBeanValidationException.getResponseMessage());
    }
}