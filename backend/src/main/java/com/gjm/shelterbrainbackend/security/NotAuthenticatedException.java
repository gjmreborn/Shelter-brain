package com.gjm.shelterbrainbackend.security;

public class NotAuthenticatedException extends ShelterBrainException {
    public NotAuthenticatedException() {
        super(401, "JWT security violation!");
    }
}
