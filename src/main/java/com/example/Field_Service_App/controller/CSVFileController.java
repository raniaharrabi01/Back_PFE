package com.example.Field_Service_App.controller;

import com.example.Field_Service_App.service.CSVFileService.CSVFileService;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CSVFileController {
    @Autowired
    CSVFileService csvFileService;
    @PostMapping("/upload")
    public List<Map<String, Object>> getFile(@RequestParam("file") MultipartFile file) throws IOException, CsvException {
        return csvFileService.getDataFromFile(file);
    }

}
