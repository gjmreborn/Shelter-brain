package com.gjm.shelterbrainbackend.security;

import org.springframework.validation.FieldError;

import java.util.List;

public class ShelterBeanValidationException extends ShelterBrainException {
    public ShelterBeanValidationException(List<FieldError> validationErrors) {
        super(422, "");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Błąd walidacji!\n");
        validationErrors.forEach(validationError -> stringBuilder.append(validationError.getDefaultMessage()).append("\n"));

        setResponseMessage(stringBuilder.toString());
    }
}
