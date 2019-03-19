package com.gjm.shelterbrainbackend.security;

public class NotAuthenticatedException extends ShelterBrainException {
    public NotAuthenticatedException(String message) {
        super(401, message);
    }
}
