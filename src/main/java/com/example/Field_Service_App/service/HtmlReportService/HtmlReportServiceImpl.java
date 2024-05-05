package com.example.Field_Service_App.service.HtmlReportService;

import com.example.Field_Service_App.model.Template;
import com.example.Field_Service_App.service.ExternalDataBaseService.ConnectionData;
import com.example.Field_Service_App.service.ExternalDataBaseService.ExternalDataBaseService;
import com.example.Field_Service_App.service.GenerateReportService.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@Service
public class HtmlReportServiceImpl implements HtmlReportService{
    @Autowired
    ReportService reportService;
    ExternalDataBaseService externalDataBaseService;
    @Override
    public String exporterReportToHTML(String SQLquery, ConnectionData data, Template modelData) {
        // Récupérer les données du rapport en appelant la fonction du service
        List<Map<String, Object>> results = externalDataBaseService.getDataFromSQLQuery(SQLquery, data);
        String code = reportService.prepareTemplateCodeSyntax(modelData);
        String processedHtml = reportService.generateReportUsingThymleaf(results, code);
        return generateAndDownloadHTML(processedHtml);
    }
    @Override
    public String generateAndDownloadHTML(String processedHtml) {
        try {
            String userDownloadsDir = System.getProperty("user.home") + File.separator + "Downloads";
            String filePath = userDownloadsDir + File.separator + "rapport.html";
            // Create a FileWriter object for the specified file path
            FileWriter fileWriter = new FileWriter(filePath);
            // Write the HTML content to the file
            fileWriter.write(processedHtml);
            // Close the FileWriter
            fileWriter.close();
            System.out.println("HTML file generated successfully: " + filePath);
            return "success";
        } catch (IOException e) {
            System.out.println("An error occurred while generating the HTML file: " + e.getMessage());
        }
        return null;
    }

}
