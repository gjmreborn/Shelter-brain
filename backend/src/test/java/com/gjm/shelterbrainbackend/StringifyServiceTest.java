package com.gjm.shelterbrainbackend;

import com.gjm.shelterbrainbackend.security.ShelterBeanValidationException;
import org.junit.Test;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class StringifyServiceTest {
    @Test
    public void stringifyValidationErrors() {
        List<FieldError> validationErrors = List.of(
                new FieldError("x", "y", "message1"),
                new FieldError("a", "b", "message2")
        );
        ShelterBeanValidationException exception = new ShelterBeanValidationException(validationErrors);

        assertEquals(422, exception.getHttpStatus());
        assertEquals("Validation error!\nmessage1\nmessage2\n", exception.getResponseMessage());
    }
}