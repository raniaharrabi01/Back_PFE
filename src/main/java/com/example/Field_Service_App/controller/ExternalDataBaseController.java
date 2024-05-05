package com.example.Field_Service_App.controller;

import com.example.Field_Service_App.service.ExternalDataBaseService.ConnectionData;
import com.example.Field_Service_App.service.ExternalDataBaseService.ExternalDataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins ="http://localhost:5173")
public class ExternalDataBaseController {
    @Autowired
    private ExternalDataBaseService databaseMetadataService;

    @PostMapping("/tablesAndColumns")
    public Map<String, List<String>> getAllTablesAndColumns(@RequestBody ConnectionData data) {
        return  databaseMetadataService.getAllTablesAndColumnsName(data);
    }

//    @PostMapping("/generate_report_columns/{tablename}")
//    public List<Map<String, Object>> getDataFromSelectColumnsList(@PathVariable String tablename,@RequestBody ReportRequest reportRequest) {
//        return databaseMetadataService.getDataFromSelectColumnsList(tablename ,reportRequest.getColonnename(), reportRequest.getData());
//    }
//
//    @PostMapping("/generate_report_SQLRequete/{requeteSQL}")
//    public List<Map<String, Object>> getDataFromSQLQuery(@PathVariable String requeteSQL,@RequestBody ConnectionData data) {
//        return  databaseMetadataService.getDataFromSQLQuery(requeteSQL , data);
//    }
}
