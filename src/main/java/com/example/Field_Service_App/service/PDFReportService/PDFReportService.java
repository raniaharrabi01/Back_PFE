package com.example.Field_Service_App.service.PDFReportService;

import com.example.Field_Service_App.model.Template;
import com.example.Field_Service_App.service.ExternalDataBaseService.ConnectionData;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface PDFReportService {
    public String exportHtmlCodeTodPdfFile(String processedHtml);
    public String ExportPDFReport(Template modelData, List<Map<String, Object>> results);
    public String exportPDFReportFromSQLquery(String Sql_query, ConnectionData data, Template template);
    public String exportPDFReportFromSelectColumns(String tablename, ConnectionData data, Template modelData , List<String> colonnename);
    public String exportPDFReportFromCSVFile(MultipartFile file, Template modelData) throws IOException, CsvException;


}
