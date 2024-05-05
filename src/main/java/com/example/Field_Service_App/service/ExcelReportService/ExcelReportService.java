package com.example.Field_Service_App.service.ExcelReportService;

import com.example.Field_Service_App.model.Template;
import com.example.Field_Service_App.service.ExternalDataBaseService.ConnectionData;
import org.springframework.stereotype.Service;

@Service
public interface ExcelReportService {
    public String generateAndDownloadExcel(String processedHtml);
    public String exporterReportToExcel(String Sql_query, ConnectionData data, Template template);
}
