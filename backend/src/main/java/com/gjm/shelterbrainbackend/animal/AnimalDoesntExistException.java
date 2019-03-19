package com.gjm.shelterbrainbackend.animal;

import com.gjm.shelterbrainbackend.security.ShelterBrainException;

public class AnimalDoesntExistException extends ShelterBrainException {
    public AnimalDoesntExistException(String message) {
        super(404, message);
    }
}
