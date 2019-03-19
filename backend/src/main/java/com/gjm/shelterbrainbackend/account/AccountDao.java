package com.gjm.shelterbrainbackend.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByName(String name);
}
