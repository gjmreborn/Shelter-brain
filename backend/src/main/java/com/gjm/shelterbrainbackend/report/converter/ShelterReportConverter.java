package com.gjm.shelterbrainbackend.report.converter;

import com.gjm.shelterbrainbackend.report.ShelterReport;

public interface ShelterReportConverter<T> {
    T convert(ShelterReport shelterReport);
}
