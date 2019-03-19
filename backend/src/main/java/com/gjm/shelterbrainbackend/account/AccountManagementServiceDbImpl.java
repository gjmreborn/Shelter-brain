package com.gjm.shelterbrainbackend.account;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.gjm.shelterbrainbackend.security.JwtSecurityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountManagementServiceDbImpl implements AccountManagementService {
    private final AccountDao accountDao;
    private final JwtSecurityManager jwtSecurityManager;

    @Override
    public void registerAccount(Account account) {
        if(accountDao.findAccountByName(account.getName()).isPresent()) {
            throw new AccountAlreadyExistsException("Pracownik \"" + account.getName() + "\" już jest zarejestrowany!");
        }

        String bcryptedPassword = BCrypt.withDefaults()
                .hashToString(12, account.getPassword().toCharArray());
        account.setPassword(bcryptedPassword);

        accountDao.save(account);
    }

    @Override
    public String loginAccount(String name, String password) {
        Optional<Account> account = accountDao.findAccountByName(name);

        if(!account.isPresent()) {
            throw new AccountDoesntExistException("Pracownik \"" + name + "\" nie istnieje!");
        }

        BCrypt.Result comparsionPasswordsResult = BCrypt.verifyer()
                .verify(password.toCharArray(), account.get().getPassword());
        if(comparsionPasswordsResult.verified) {
            // correct credentials ==> log in ==> create JWT
            return jwtSecurityManager.idToJwt(account.get().getId());
        } else {
            throw new WrongAccountPasswordException("Złe hasło!");
        }
    }


    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Account findAccountById(long id) {
        return accountDao.findById(id).orElse(null);
    }
}
