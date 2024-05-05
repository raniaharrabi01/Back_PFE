package com.example.Field_Service_App.service.CSVFileService;

import com.example.Field_Service_App.model.Template;
import org.springframework.web.multipart.MultipartFile;

public class FileData {
    private MultipartFile formData;
    private Template modelData;

    public MultipartFile getFormData() {
        return formData;
    }
    public Template getModelData() {
        return modelData;
    }
}
