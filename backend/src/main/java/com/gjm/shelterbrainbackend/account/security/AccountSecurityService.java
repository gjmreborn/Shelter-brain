package com.gjm.shelterbrainbackend.account.security;

public interface AccountSecurityService {
    void checkIfAccountIsValidByJwt(String jwt);
    void checkIfAccountIsValidById(long id);
}
