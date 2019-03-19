package com.gjm.shelterbrainbackend.report.converter.factory;

import com.gjm.shelterbrainbackend.report.converter.CsvShelterReportConverter;
import com.gjm.shelterbrainbackend.report.converter.PdfShelterReportConverter;
import com.gjm.shelterbrainbackend.report.converter.ShelterReportConverter;
import com.gjm.shelterbrainbackend.report.converter.WebShelterReportConverter;

public class ShelterReportConverterFactory {
    public static ShelterReportConverter getInstanceByFormat(String format) {
        switch(format) {
            case "web":
                return new WebShelterReportConverter();
            case "pdf":
                return new PdfShelterReportConverter();
            case "csv":
                return new CsvShelterReportConverter();
            default:
                throw new IllegalArgumentException("Nieznany format raportu - " + format);
        }
    }
}
