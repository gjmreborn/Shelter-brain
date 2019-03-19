package com.gjm.shelterbrainbackend.report.converter;

import com.gjm.shelterbrainbackend.animal.Animal;
import com.gjm.shelterbrainbackend.report.ShelterReport;
import lombok.EqualsAndHashCode;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@EqualsAndHashCode
public class CsvShelterReportConverter implements ShelterReportConverter<String> {
    private ShelterReport shelterReport;

    @Override
    public String convert(ShelterReport shelterReport) {
        this.shelterReport = shelterReport;

        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BufferedWriter csvOut = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream));
            CSVPrinter csvPrinter = new CSVPrinter(csvOut, CSVFormat.DEFAULT.withHeader(ShelterReport.getAnimalsHeaders()))) {

            addRows(csvPrinter);
            addAdditionalData(csvOut);

            csvOut.flush();
            return new String(byteArrayOutputStream.toByteArray());
        } catch(IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    private void addRows(CSVPrinter csv) throws IOException {
        List<Animal> animals = shelterReport.getAnimals();

        for(int i = 0; i < animals.size();i++) {
            Animal animal = animals.get(i);

            csv.printRecord(
                    i + 1,
                    animal.getName(),
                    animal.getGender().toString(),
                    animal.getAge(),
                    animal.getDateOfAdd().format(ShelterReport.getDateTimeFormatter())
            );
        }
    }

    private void addAdditionalData(BufferedWriter csv) throws IOException {
        csv.newLine();
        csv.write(shelterReport.getOccupancyMessage());
        csv.newLine();
        csv.write(shelterReport.getShelterStatus().toString());
    }
}
