package com.gjm.shelterbrainbackend.animal;

import java.util.List;

public interface AnimalService {
    void addAnimal(Animal animal);
    void deleteAnimal(long id);
    int getMaxAnimals();
    List<Animal> findAllAnimals();
}
