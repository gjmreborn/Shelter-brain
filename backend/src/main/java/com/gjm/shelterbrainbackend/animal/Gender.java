package com.gjm.shelterbrainbackend.animal;

public enum Gender {
    MALE, FEMALE;

    @Override
    public String toString() {
        return this.name().equals("MALE") ? "Meska" : "Zenska";
    }
}
