package com.gjm.shelterbrainbackend.account;

import com.gjm.shelterbrainbackend.account.security.AccountSecurityService;
import com.gjm.shelterbrainbackend.security.JwtSecurityManager;
import com.gjm.shelterbrainbackend.security.ShelterBeanValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class AccountController {
    private final AccountManagementService accountManagementService;
    private final AccountSecurityService accountSecurityService;
    private final JwtSecurityManager jwtSecurityManager;

    @PostMapping("/accounts")
    public void registerAccount(@RequestBody @Valid Account account, BindingResult validationResult) {
        if(validationResult.hasErrors()) {
            throw new ShelterBeanValidationException(validationResult.getFieldErrors());
        }

        accountManagementService.registerAccount(account);
    }

    @PostMapping("/login")
    public ResponseEntity loginAccount(@RequestBody @Valid LoginDto loginDto, BindingResult validationResult) {
        if(validationResult.hasErrors()) {
            throw new ShelterBeanValidationException(validationResult.getFieldErrors());
        }
        String jwt = accountManagementService.loginAccount(loginDto.getName(), loginDto.getPassword());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("x-auth", jwt);
        httpHeaders.set("Access-Control-Expose-Headers", "x-auth");

        return new ResponseEntity<>(
                "",
                httpHeaders,
                HttpStatus.OK
        );
    }

    @GetMapping("/account")
    public Account getAccount(@RequestHeader(value = "x-auth", required = false) String jwt) {
        long accountId = jwtSecurityManager.jwtToId(jwt);
        accountSecurityService.checkIfAccountIsValidById(accountId);

        return accountManagementService.findAccountById(accountId);
    }
}
