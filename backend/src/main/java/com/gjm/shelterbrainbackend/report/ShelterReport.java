package com.gjm.shelterbrainbackend.report;

import com.gjm.shelterbrainbackend.animal.Animal;
import com.gjm.shelterbrainbackend.animal.ShelterStatus;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class ShelterReport {
    private List<Animal> animals;
    private int maxAnimals;
    private int occupiedPlaces;
    private ShelterStatus shelterStatus;

    public static String[] getAnimalsHeaders() {
        return new String[] {"Lp.", "Imie", "Plec", "Wiek", "Data przybycia"};
    }

    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public String getOccupancyMessage() {
        return occupiedPlaces + " / " + maxAnimals + " zajetych miejsc";
    }
}
