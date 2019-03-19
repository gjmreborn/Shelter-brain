package com.gjm.shelterbrainbackend.report;

import com.gjm.shelterbrainbackend.animal.Animal;
import com.gjm.shelterbrainbackend.animal.AnimalService;
import com.gjm.shelterbrainbackend.animal.ShelterStatus;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class ReportServiceTest {
    private ReportService reportService;
    private AnimalService animalService;
    private List<Animal> animals;

    @Before
    public void before() {
        animals = new LinkedList<>();
        for(int i = 0; i < 5;i++) {
            animals.add(new Animal());
        }

        animalService = mock(AnimalService.class);
        when(animalService.getMaxAnimals()).thenReturn(12);
        when(animalService.findAllAnimals()).thenReturn(animals);

        reportService = new ReportService(animalService);
    }

    @Test
    public void getShelterReport() {
        ShelterReport shelterReport = reportService.getShelterReport();

        verify(animalService, times(1)).getMaxAnimals();
        verify(animalService, times(1)).findAllAnimals();

        assertEquals(animals, shelterReport.getAnimals());
        assertEquals(12, shelterReport.getMaxAnimals());
        assertEquals(ShelterStatus.HAS_PLACE, shelterReport.getShelterStatus());
        assertEquals(5, shelterReport.getOccupiedPlaces());
    }
}