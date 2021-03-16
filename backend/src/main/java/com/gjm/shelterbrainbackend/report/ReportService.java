package com.gjm.shelterbrainbackend.report;

import com.gjm.shelterbrainbackend.animal.Animal;
import com.gjm.shelterbrainbackend.animal.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {
    private final AnimalService animalService;

    public ShelterReport getShelterReport() {
        List<Animal> animals = animalService.findAllAnimals();
        int maxAnimals = animalService.getMaxAnimals();

        return new ShelterReport(animals, maxAnimals);
    }
}
