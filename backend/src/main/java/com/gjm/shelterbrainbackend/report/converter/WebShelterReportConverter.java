package com.gjm.shelterbrainbackend.report.converter;

import com.gjm.shelterbrainbackend.report.ShelterReport;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class WebShelterReportConverter implements ShelterReportConverter<ShelterReport> {
    @Override
    public ShelterReport convert(ShelterReport shelterReport) {
        return shelterReport;
    }
}
