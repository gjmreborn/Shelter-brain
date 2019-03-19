package com.gjm.shelterbrainbackend.animal;

import com.gjm.shelterbrainbackend.security.ShelterBrainException;

public class ShelterIsFullException extends ShelterBrainException {
    public ShelterIsFullException(String message) {
        super(409, message);
    }
}
