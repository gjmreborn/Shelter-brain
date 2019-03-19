package com.gjm.shelterbrainbackend.account;

import java.util.List;

public interface AccountManagementService {
    void registerAccount(Account account);

    /**
     * Returns encoded JWT with logged in account's ID
     */
    String loginAccount(String name, String password);

    List<Account> findAll();
    Account findAccountById(long id);
}
