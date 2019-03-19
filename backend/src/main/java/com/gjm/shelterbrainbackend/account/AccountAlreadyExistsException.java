package com.gjm.shelterbrainbackend.account;

import com.gjm.shelterbrainbackend.security.ShelterBrainException;

public class AccountAlreadyExistsException extends ShelterBrainException {
    public AccountAlreadyExistsException(String message) {
        super(409, message);
    }
}
