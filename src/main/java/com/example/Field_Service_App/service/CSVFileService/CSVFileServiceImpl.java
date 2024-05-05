package com.example.Field_Service_App.service.CSVFileService;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.*;


@Service
public class CSVFileServiceImpl implements CSVFileService {
    @Override
    public List<Map<String, Object>> getDataFromFile(MultipartFile file) {
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()));
            List<String[]> rows = reader.readAll();
            reader.close();
            List<Map<String, Object>> columns = transpose(rows);
            return columns;
        } catch (IOException | CsvException e) {
            // Handle IOException and CsvException
            e.printStackTrace(); // Log the exception
            return Collections.emptyList(); // Return an empty list or handle the error accordingly
        }
    }
    private List<Map<String, Object>> transpose(List<String[]> rows) {
        List<Map<String, Object>> columns = new ArrayList<>();
        if (!rows.isEmpty()) {
            int rowCount = rows.size();
            int columnCount = rows.get(0).length;

            // Extract column names from the first row
            String[] columnNames = rows.get(0);

            // Iterate over each column (excluding the first row)
            for (int i = 0; i < columnCount; i++) {
                Map<String, Object> columnMap = new HashMap<>();
                String columnName = columnNames[i]; // Use column name from the first row

                // Create a list to store the column data
                List<Object> columnData = new ArrayList<>();

                // Iterate over each row (excluding the first row) and add the value to the corresponding column
                for (int j = 1; j < rowCount; j++) {
                    columnData.add(rows.get(j)[i]);
                }

                // Add the column data to the column map
                columnMap.put(columnName, columnData);

                // Add the column map to the list of columns
                columns.add(columnMap);
            }
        }
        return columns;
    }
}