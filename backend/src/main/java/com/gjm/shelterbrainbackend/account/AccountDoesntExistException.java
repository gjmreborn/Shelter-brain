package com.gjm.shelterbrainbackend.account;

import com.gjm.shelterbrainbackend.security.ShelterBrainException;

public class AccountDoesntExistException extends ShelterBrainException {
    public AccountDoesntExistException(String message) {
        super(404, message);
    }
}
