package com.gjm.shelterbrainbackend.account;

import com.gjm.shelterbrainbackend.account.security.AccountSecurityService;
import com.gjm.shelterbrainbackend.security.JwtSecurityManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Objects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AccountControllerTest {
    private AccountController accountController;
    private AccountManagementService accountManagementService;
    private JwtSecurityManager jwtSecurityManager;
    private LoginDto loginDto;
    private Account account;

    @Before
    public void before() {
        loginDto = new LoginDto();
        loginDto.setName("user123");
        loginDto.setPassword("password123");

        account = new Account();
        account.setName("user123");

        accountManagementService = mock(AccountManagementService.class);
        when(accountManagementService.loginAccount(loginDto.getName(), loginDto.getPassword())).thenReturn("jwt123");
        when(accountManagementService.findAccountById(5L)).thenReturn(account);

        jwtSecurityManager = mock(JwtSecurityManager.class);
        when(jwtSecurityManager.jwtToId("jwt123")).thenReturn(5L);

        accountController = new AccountController(accountManagementService, mock(AccountSecurityService.class), jwtSecurityManager);
    }

    @Test
    public void registerAccount() {
        accountController.registerAccount(account, mock(BindingResult.class));

        verify(accountManagementService, times(1)).registerAccount(account);
    }

    @Test
    public void loginAccount() {
        ResponseEntity responseEntity = accountController.loginAccount(loginDto, mock(BindingResult.class));

        verify(accountManagementService, times(1)).loginAccount(loginDto.getName(), loginDto.getPassword());
        assertEquals("jwt123", Objects.requireNonNull(responseEntity.getHeaders().get("x-auth")).get(0));
        assertEquals("x-auth", Objects.requireNonNull(responseEntity.getHeaders().get("Access-Control-Expose-Headers")).get(0));
        assertEquals("", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getAccount() {
        Account account = accountController.getAccount("jwt123");

        assertEquals("user123", account.getName());
    }
}