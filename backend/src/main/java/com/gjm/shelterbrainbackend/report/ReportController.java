package com.gjm.shelterbrainbackend.report;

import com.gjm.shelterbrainbackend.report.converter.ShelterReportConverter;
import com.gjm.shelterbrainbackend.report.converter.factory.ShelterReportConverterFactory;
import com.gjm.shelterbrainbackend.report.converter.factory.ShelterReportHttpHeadersFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/report")
    public ResponseEntity getShelterReport(@RequestParam(value = "format", defaultValue = "web", required = false) String format) {
        ShelterReport shelterReport = reportService.getShelterReport();
        ShelterReportConverter shelterReportConverter = ShelterReportConverterFactory.getInstanceByFormat(format);

        return new ResponseEntity<>(
                shelterReportConverter.convert(shelterReport),
                ShelterReportHttpHeadersFactory.getHttpHeadersByFormat(format),
                HttpStatus.OK
        );
    }
}
