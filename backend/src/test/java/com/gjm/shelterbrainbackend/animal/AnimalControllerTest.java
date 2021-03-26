package com.gjm.shelterbrainbackend.animal;

import com.gjm.shelterbrainbackend.account.security.AccountSecurityService;
import com.gjm.shelterbrainbackend.security.NotAuthenticatedException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindingResult;

import static org.mockito.Mockito.*;

public class AnimalControllerTest {
    private AnimalController animalController;
    private AnimalService animalService;
    private AccountSecurityService accountSecurityService;

    @Before
    public void before() {
        animalService = mock(AnimalService.class);
        accountSecurityService = mock(AccountSecurityService.class);
        animalController = new AnimalController(animalService, accountSecurityService);
    }

    @Test(expected = NotAuthenticatedException.class)
    public void jwtSecurity() {
        accountSecurityService = mock(AccountSecurityService.class);
        doThrow(NotAuthenticatedException.class).when(accountSecurityService).checkIfAccountIsValidByJwt(any(String.class));

        animalController = new AnimalController(animalService, accountSecurityService);

        animalController.addAnimal("x", new Animal(), mock(BindingResult.class));
        animalController.deleteAnimal("x", 1L);
    }

    @Test
    public void addAnimal() {
        Animal animal = new Animal();

        animalController.addAnimal("x", animal, mock(BindingResult.class));

        verify(animalService, times(1)).addAnimal(animal);
    }

    @Test
    public void deleteAnimal() {
        animalController.deleteAnimal("x", 1L);

        verify(animalService, times(1)).deleteAnimal(1L);
    }
}