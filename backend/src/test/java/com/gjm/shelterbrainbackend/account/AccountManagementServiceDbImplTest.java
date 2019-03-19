package com.gjm.shelterbrainbackend.account;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.gjm.shelterbrainbackend.security.JwtSecurityManager;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AccountManagementServiceDbImplTest {
    private AccountManagementService accountManagementService;
    private AccountDao accountDao;
    private JwtSecurityManager jwtSecurityManager;
    private List<Account> accounts;
    private Account account;

    @Before
    public void before() {
        accounts = new LinkedList<>();
        for(int i = 0; i < 5;i++) {
            accounts.add(new Account());
        }
        account = new Account();
        account.setName("name123");
        account.setPassword("123");

        Account exists = new Account();
        exists.setId(5L);
        exists.setName("exists");
        exists.setPassword(BCrypt.withDefaults().hashToString(12, "pass123".toCharArray()));

        accountDao = mock(AccountDao.class);
        when(accountDao.findAll()).thenReturn(accounts);
        when(accountDao.findById(1L)).thenReturn(Optional.empty());
        when(accountDao.findById(2L)).thenReturn(Optional.of(account));
        when(accountDao.findAccountByName("exists")).thenReturn(Optional.of(exists));
        when(accountDao.findAccountByName(account.getName())).thenReturn(Optional.empty());

        jwtSecurityManager = mock(JwtSecurityManager.class);
        when(jwtSecurityManager.idToJwt(5L)).thenReturn("jwt123");

        accountManagementService = new AccountManagementServiceDbImpl(accountDao, jwtSecurityManager);
    }

    @Test
    public void registerAccount() {
        accountManagementService.registerAccount(account);

        verify(accountDao, times(1)).save(any(Account.class));
    }

    @Test(expected = AccountAlreadyExistsException.class)
    public void registerAccountAlreadyExists() {
        Account account = new Account();
        account.setName("exists");

        accountManagementService.registerAccount(account);
    }

    @Test
    public void loginAccount() {
        String jwt = accountManagementService.loginAccount("exists", "pass123");

        assertEquals("jwt123", jwt);
    }

    @Test(expected = AccountDoesntExistException.class)
    public void loginAccountDoesntExist() {
        accountManagementService.loginAccount("name123", "----");
    }

    @Test(expected = WrongAccountPasswordException.class)
    public void loginAccountWrongPassword() {
        accountManagementService.loginAccount("exists", "bad password");
    }

    @Test
    public void findAll() {
        assertEquals(accounts, accountManagementService.findAll());
        verify(accountDao, times(1)).findAll();
    }

    @Test
    public void findAccountById() {
        assertNull(accountManagementService.findAccountById(1L));
        assertEquals(account, accountManagementService.findAccountById(2L));
    }
}