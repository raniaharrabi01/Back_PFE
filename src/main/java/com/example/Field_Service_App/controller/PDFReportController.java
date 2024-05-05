package com.example.Field_Service_App.controller;

import com.example.Field_Service_App.model.Template;
import com.example.Field_Service_App.service.CSVFileService.FileData;
import com.example.Field_Service_App.service.GenerateReportService.ReportRequest;
import com.example.Field_Service_App.service.PDFReportService.PDFReportService;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/ExportPDFReport")
@CrossOrigin(origins ="http://localhost:5173")
public class PDFReportController {
    @Autowired
    PDFReportService pdfReportService;
    @PostMapping("/FromSQLquery/{SQLquery}")
    public String exportPDFReportFromSQLquery(@PathVariable String SQLquery, @RequestBody ReportRequest reportRequest) {
        return pdfReportService.exportPDFReportFromSQLquery(SQLquery, reportRequest.getData(),reportRequest.getModelData());
    }
    @PostMapping("/FromSelectColumns/{tablename}")
    public String exportPDFReportFromSelectColumns(@PathVariable String tablename, @RequestBody ReportRequest reportRequest) {
        return pdfReportService.exportPDFReportFromSelectColumns(tablename, reportRequest.getData(), reportRequest.getModelData(), reportRequest.getColonnename());
    }
    @PostMapping("/FromCSVFile")
    public String exportPDFReportFromCSVFile( @RequestParam("modelData") Template jsonData, @RequestParam("file") MultipartFile file) {
        try {
            return pdfReportService.exportPDFReportFromCSVFile(file, jsonData);
        } catch (IOException | CsvException e) {
            e.printStackTrace(); // Log the exception
            return "Error exporting PDF report: " + e.getMessage(); // Return an error message
        }
    }

}
