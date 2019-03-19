package com.gjm.shelterbrainbackend.report;

import com.gjm.shelterbrainbackend.animal.Animal;
import com.gjm.shelterbrainbackend.animal.AnimalService;
import com.gjm.shelterbrainbackend.animal.ShelterStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class ReportService {
    private final AnimalService animalService;

    public ShelterReport getShelterReport() {
        List<Animal> animals = animalService.findAllAnimals();
        ShelterReport shelterReport = new ShelterReport();
        int maxAnimals = animalService.getMaxAnimals();

        shelterReport.setAnimals(animals);
        shelterReport.setMaxAnimals(maxAnimals);
        shelterReport.setShelterStatus(animals.size() == maxAnimals ? ShelterStatus.FULL : ShelterStatus.HAS_PLACE);
        shelterReport.setOccupiedPlaces(animals.size());

        return shelterReport;
    }
}
