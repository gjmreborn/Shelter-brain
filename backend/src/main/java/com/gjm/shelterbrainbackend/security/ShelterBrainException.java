package com.gjm.shelterbrainbackend.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ShelterBrainException extends RuntimeException {
    private int httpStatus;
    private String responseMessage;
}
