package com.gjm.shelterbrainbackend.account.security;

import com.gjm.shelterbrainbackend.account.AccountDao;
import com.gjm.shelterbrainbackend.security.JwtSecurityManager;
import com.gjm.shelterbrainbackend.security.NotAuthenticatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountSecurityServiceDbImpl implements AccountSecurityService {
    private final JwtSecurityManager jwtSecurityManager;
    private final AccountDao accountDao;

    @Override
    public void checkIfAccountIsValidByJwt(String jwt) {
        checkIfAccountIsValidById(jwtSecurityManager.jwtToId(jwt));
    }

    @Override
    public void checkIfAccountIsValidById(long id) {
        if(!accountDao.findById(id).isPresent()) {
            throw new NotAuthenticatedException();
        }
    }
}
