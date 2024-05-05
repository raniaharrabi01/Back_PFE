package com.example.Field_Service_App.service.GenerateReportService;


import com.example.Field_Service_App.model.Template;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ReportService {
    public String generateReportUsingThymleaf(List<Map<String, Object>> results, String code);
    public String prepareTemplateCodeSyntax(Template template);

    }
