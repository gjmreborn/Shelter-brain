package com.gjm.shelterbrainbackend.animal;

import com.gjm.shelterbrainbackend.account.security.AccountSecurityService;
import com.gjm.shelterbrainbackend.security.ShelterBeanValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;
    private final AccountSecurityService accountSecurityService;

    @PostMapping("/animals")
    public void addAnimal(@RequestHeader(value = "x-auth", required = false) String jwt, @RequestBody @Valid Animal animal, BindingResult validationResult) {
        accountSecurityService.checkIfAccountIsValidByJwt(jwt);
        if(validationResult.hasErrors()) {
            throw new ShelterBeanValidationException(validationResult.getFieldErrors());
        }

        animalService.addAnimal(animal);
    }

    @DeleteMapping("/animals/{id}")
    public void deleteAnimal(@RequestHeader(value = "x-auth", required = false) String jwt, @PathVariable("id") long id) {
        accountSecurityService.checkIfAccountIsValidByJwt(jwt);

        animalService.deleteAnimal(id);
    }
}
