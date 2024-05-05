package com.example.Field_Service_App.service.HtmlReportService;

import com.example.Field_Service_App.model.Template;
import com.example.Field_Service_App.service.ExternalDataBaseService.ConnectionData;
import org.springframework.stereotype.Service;

@Service
public interface HtmlReportService {
    public String generateAndDownloadHTML(String processedHtml);
    public String exporterReportToHTML(String Sql_query, ConnectionData data, Template template );

}
