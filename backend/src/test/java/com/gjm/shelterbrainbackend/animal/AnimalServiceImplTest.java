package com.gjm.shelterbrainbackend.animal;

import com.gjm.shelterbrainbackend.account.AccountEmailNotificationService;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class AnimalServiceImplTest {
    private AnimalService animalService;
    private AnimalDao animalDao;
    private AccountEmailNotificationService accountEmailNotificationService;

    @Before
    public void before() {
        animalDao = mock(AnimalDao.class);
        when(animalDao.findById(1L)).thenReturn(Optional.empty());
        when(animalDao.findById(2L)).thenReturn(Optional.of(new Animal()));

        List<Animal> animals = new LinkedList<>();
        for(int i = 0; i < 5;i++) {
            animals.add(new Animal());
        }
        when(animalDao.findAll()).thenReturn(animals);

        accountEmailNotificationService = mock(AccountEmailNotificationService.class);

        animalService = new AnimalServiceImpl(5, animalDao, accountEmailNotificationService);
    }

    @Test(expected = ShelterIsFullException.class)
    public void addAnimalShelterIsFullException() {
        animalService.addAnimal(new Animal());
    }

    @Test
    public void addAnimalShelterHasPlace() {
        animalDao = mock(AnimalDao.class);
        when(animalDao.findAll()).thenReturn(new LinkedList<>());

        animalService = new AnimalServiceImpl(5, animalDao, accountEmailNotificationService);

        Animal animal = new Animal();
        animal.setName("testAnimal");

        animalService.addAnimal(animal);

        verify(animalDao, times(1)).save(animal);
        verify(accountEmailNotificationService, times(1)).sendWarningEmailsIfNecessary();
    }

    @Test(expected = AnimalDoesntExistException.class)
    public void deleteAnimalDoesntExistException() {
        animalService.deleteAnimal(1L);
    }

    @Test
    public void deleteAnimalExists() {
        animalService.deleteAnimal(2L);

        verify(animalDao, times(1)).deleteById(2L);
    }
}