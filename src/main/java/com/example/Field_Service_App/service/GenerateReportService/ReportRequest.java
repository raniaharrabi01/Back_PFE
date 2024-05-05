package com.example.Field_Service_App.service.GenerateReportService;

import com.example.Field_Service_App.model.Template;
import com.example.Field_Service_App.service.ExternalDataBaseService.ConnectionData;

import java.util.List;


public class ReportRequest {
    private ConnectionData data;
    private Template modelData;
    private List<String> colonnename;

    public ConnectionData getData() {
        return data;
    }

    public Template getModelData() {
        return modelData;
    }

    public List<String> getColonnename() {
        return colonnename;
    }
}
