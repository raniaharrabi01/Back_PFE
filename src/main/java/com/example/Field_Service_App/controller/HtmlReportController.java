package com.example.Field_Service_App.controller;

import com.example.Field_Service_App.service.GenerateReportService.ReportRequest;
import com.example.Field_Service_App.service.HtmlReportService.HtmlReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ExportHTMLReport")
@CrossOrigin(origins ="http://localhost:5173")
public class HtmlReportController {
    @Autowired
    HtmlReportService htmlReportService;

    @PostMapping("/FromSQLquery/{SQLquery}")
    public String exportHTMLReport(@PathVariable String SQLquery, @RequestBody ReportRequest reportRequest ) {
        return htmlReportService.exporterReportToHTML(SQLquery,reportRequest.getData(),reportRequest.getModelData());
    }

}
