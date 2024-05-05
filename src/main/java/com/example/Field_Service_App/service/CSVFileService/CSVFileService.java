package com.example.Field_Service_App.service.CSVFileService;

import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface CSVFileService {
    public List<Map<String, Object>> getDataFromFile(MultipartFile file) throws IOException, CsvException;

}
