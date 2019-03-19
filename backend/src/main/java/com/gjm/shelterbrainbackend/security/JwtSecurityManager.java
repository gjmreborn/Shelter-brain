package com.gjm.shelterbrainbackend.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

import java.security.Key;

@RequiredArgsConstructor
public class JwtSecurityManager {
    private final Key key;

    public String idToJwt(long id) {
        return Jwts.builder()
                .setSubject(id + "")
                .signWith(key)
                .compact();
    }

    public long jwtToId(String jwt) {
        try {
            if(jwt == null) {
                throw new NotAuthenticatedException("Błąd bezpieczeństwa JWT!");
            }

            String stringifedId = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();

            return Long.parseLong(stringifedId);
        } catch(JwtException exc) {
            throw new NotAuthenticatedException("Błąd bezpieczeństwa JWT!");
        }
    }
}
