package com.gjm.shelterbrainbackend.account;

import com.gjm.shelterbrainbackend.security.ShelterBrainException;

public class WrongAccountPasswordException extends ShelterBrainException {
    public WrongAccountPasswordException(String message) {
        super(401, message);
    }
}
