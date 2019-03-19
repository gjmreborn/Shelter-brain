package com.gjm.shelterbrainbackend.report;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShelterReportTest {
    @Test
    public void getOccupancyMessage() {
        ShelterReport shelterReport = new ShelterReport();

        shelterReport.setOccupiedPlaces(5);
        shelterReport.setMaxAnimals(12);

        assertEquals("5 / 12 zajetych miejsc", shelterReport.getOccupancyMessage());
    }
}