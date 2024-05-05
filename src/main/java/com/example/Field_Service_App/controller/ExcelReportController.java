package com.example.Field_Service_App.controller;

import com.example.Field_Service_App.service.ExcelReportService.ExcelReportService;
import com.example.Field_Service_App.service.GenerateReportService.ReportRequest;
import com.example.Field_Service_App.service.GenerateReportService.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ExportExcelReport")
@CrossOrigin(origins ="http://localhost:5173")
public class ExcelReportController {
    @Autowired
    ExcelReportService excelReportService;
    @PostMapping("/FromSQLquery/{SQLquery}")
    public String exportExcelReport(@PathVariable String SQLquery, @RequestBody ReportRequest reportRequest){
        return  excelReportService.exporterReportToExcel(SQLquery,reportRequest.getData(),reportRequest.getModelData());
    }
}
