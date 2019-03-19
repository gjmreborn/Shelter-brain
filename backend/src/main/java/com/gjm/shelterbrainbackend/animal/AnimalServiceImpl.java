package com.gjm.shelterbrainbackend.animal;

import com.gjm.shelterbrainbackend.account.AccountEmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {
    private final AnimalDao animalDao;
    private final AccountEmailNotificationService accountEmailNotificationService;

    private int maxAnimals;

    @Autowired                                  // @Lazy because there is circular dependency
    public AnimalServiceImpl(@Value("${shelter.max_animals}") int maxAnimals, AnimalDao animalDao, @Lazy AccountEmailNotificationService accountEmailNotificationService) {
        this.maxAnimals = maxAnimals;
        this.animalDao = animalDao;
        this.accountEmailNotificationService = accountEmailNotificationService;
    }

    @Override
    public void addAnimal(Animal animal) {
        if(animalDao.findAll().size() == maxAnimals) {
            throw new ShelterIsFullException("Schronisko jest pełne!");
        }

        animalDao.save(animal);
        accountEmailNotificationService.sendWarningEmailsIfNecessary();
    }

    @Override
    public void deleteAnimal(long id) {
        if(!animalDao.findById(id).isPresent()) {
            throw new AnimalDoesntExistException("Zwierzę o identyfikatorze równym " + id + " nie istnieje!");
        }

        animalDao.deleteById(id);
    }

    @Override
    public int getMaxAnimals() {
        return this.maxAnimals;
    }

    @Override
    public List<Animal> findAllAnimals() {
        return animalDao.findAll();
    }
}
