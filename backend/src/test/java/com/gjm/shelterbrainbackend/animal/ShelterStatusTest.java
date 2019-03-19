package com.gjm.shelterbrainbackend.animal;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShelterStatusTest {
    @Test
    public void toStringFull() {
        assertEquals("Brak miejsc", ShelterStatus.FULL.toString());
    }

    @Test
    public void toStringHasPlace() {
        assertEquals("Jest jeszcze wolne miejsce", ShelterStatus.HAS_PLACE.toString());
    }
}