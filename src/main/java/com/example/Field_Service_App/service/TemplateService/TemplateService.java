package com.example.Field_Service_App.service.TemplateService;

import com.example.Field_Service_App.model.Template;
import org.springframework.stereotype.Service;

@Service
public interface TemplateService {
        public String saveTemplate(Template template);
        public String getAllTemplate();
        public String deleteTemplate(String name);
        public Boolean verifyTemplateNameExist(String name);
        public TemplateData getDataTemplate(String name);
        public String ModifyTemplateData(Template template);

        }
