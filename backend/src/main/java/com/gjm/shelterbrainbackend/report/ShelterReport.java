package com.gjm.shelterbrainbackend.report;

import com.gjm.shelterbrainbackend.animal.Animal;
import com.gjm.shelterbrainbackend.animal.ShelterStatus;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Data
public class ShelterReport {
    private List<Animal> animals;
    private int maxAnimals;
    private int occupiedPlaces;
    private ShelterStatus shelterStatus;

    public ShelterReport() {
        this(Collections.emptyList(), 0);
    }

    public ShelterReport(List<Animal> animals, int maxAnimals) {
        this.animals = animals;
        this.maxAnimals = maxAnimals;
        this.occupiedPlaces = animals.size();
        this.shelterStatus = (occupiedPlaces == maxAnimals) ? ShelterStatus.FULL : ShelterStatus.HAS_PLACE;
    }

    public static List<String> getReportHeaders() {
        return List.of("No.", "Name", "Gender", "Age", "Date of arrival");
    }

    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public String getOccupancyMessage() {
        return occupiedPlaces + " / " + maxAnimals + " occupied places";
    }
}
