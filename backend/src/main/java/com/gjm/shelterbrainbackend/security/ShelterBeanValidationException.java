package com.gjm.shelterbrainbackend.security;

import com.gjm.shelterbrainbackend.StringifyService;
import org.springframework.validation.FieldError;

import java.util.List;

public class ShelterBeanValidationException extends ShelterBrainException {
    public ShelterBeanValidationException(List<FieldError> validationErrors) {
        super(422, "");
        StringifyService stringifyService = new StringifyService();

        setResponseMessage(stringifyService.stringifyValidationErrors(validationErrors));
    }
}
