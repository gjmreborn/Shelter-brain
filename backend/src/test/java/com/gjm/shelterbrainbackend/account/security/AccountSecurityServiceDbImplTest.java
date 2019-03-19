package com.gjm.shelterbrainbackend.account.security;

import com.gjm.shelterbrainbackend.account.Account;
import com.gjm.shelterbrainbackend.account.AccountDao;
import com.gjm.shelterbrainbackend.security.JwtSecurityManager;
import com.gjm.shelterbrainbackend.security.NotAuthenticatedException;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountSecurityServiceDbImplTest {
    private AccountSecurityService accountSecurityService;
    private JwtSecurityManager jwtSecurityManager;
    private AccountDao accountDao;

    @Before
    public void before() {
        jwtSecurityManager = mock(JwtSecurityManager.class);
        when(jwtSecurityManager.jwtToId("exception")).thenReturn(1L);
        when(jwtSecurityManager.jwtToId("ok")).thenReturn(2L);

        accountDao = mock(AccountDao.class);
        when(accountDao.findById(1L)).thenReturn(Optional.empty());
        when(accountDao.findById(2L)).thenReturn(Optional.of(new Account()));

        accountSecurityService = new AccountSecurityServiceDbImpl(jwtSecurityManager, accountDao);
    }

    @Test
    public void checkIfAccountIsValidByJwt() {
        accountSecurityService.checkIfAccountIsValidByJwt("ok");
    }

    @Test(expected = NotAuthenticatedException.class)
    public void checkIfAccountIsValidByJwtException() {
        accountSecurityService.checkIfAccountIsValidByJwt("exception");
    }

    @Test
    public void checkIfAccountIsValidById() {
        accountSecurityService.checkIfAccountIsValidById(2L);
    }

    @Test(expected = NotAuthenticatedException.class)
    public void checkIfAccountIsValidByIdException() {
        accountSecurityService.checkIfAccountIsValidById(1L);
    }
}