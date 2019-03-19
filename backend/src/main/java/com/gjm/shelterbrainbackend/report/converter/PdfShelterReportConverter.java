package com.gjm.shelterbrainbackend.report.converter;

import com.gjm.shelterbrainbackend.animal.Animal;
import com.gjm.shelterbrainbackend.report.ShelterReport;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@EqualsAndHashCode
public class PdfShelterReportConverter implements ShelterReportConverter<byte[]> {
    private ShelterReport shelterReport;

    @Override
    public byte[] convert(ShelterReport shelterReport) {
        this.shelterReport = shelterReport;

        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            Document pdf = new Document();
            PdfWriter.getInstance(pdf, byteArrayOutputStream);
            pdf.open();

            PdfPTable animalsTable = new PdfPTable(5);
            addHeader(animalsTable);
            addRows(animalsTable);

            pdf.add(animalsTable);
            addAdditionalData(pdf);

            pdf.close();
            return byteArrayOutputStream.toByteArray();
        } catch(IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    private void addHeader(PdfPTable table) {
        Stream.of(ShelterReport.getAnimalsHeaders())
                .forEach(cell -> {
                    PdfPCell headerCell = new PdfPCell();

                    headerCell.setBackgroundColor(Color.LIGHT_GRAY);
                    headerCell.setBorderWidth(2);
                    headerCell.setPhrase(new Phrase(cell));

                    table.addCell(headerCell);
                });
    }

    private void addRows(PdfPTable table) {
        List<Animal> animals = shelterReport.getAnimals();

        for(int i = 0; i < animals.size();i++) {
            Animal animal = animals.get(i);

            table.addCell((i + 1) + "");
            table.addCell(animal.getName());
            table.addCell(animal.getGender().toString());
            table.addCell(animal.getAge() + "");
            table.addCell(animal.getDateOfAdd().format(ShelterReport.getDateTimeFormatter()));
        }
    }

    private void addAdditionalData(Document pdf) {
        pdf.add(new Paragraph(shelterReport.getOccupancyMessage()));
        pdf.add(new Paragraph(shelterReport.getShelterStatus().toString()));
    }
}
