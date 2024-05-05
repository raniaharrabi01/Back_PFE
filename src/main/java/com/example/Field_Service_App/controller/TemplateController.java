package com.example.Field_Service_App.controller;
import com.example.Field_Service_App.model.Template;
import com.example.Field_Service_App.service.TemplateService.TemplateData;
import com.example.Field_Service_App.service.TemplateService.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Template")
@CrossOrigin(origins ="http://localhost:5173")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    @PostMapping("/save")
    @ResponseBody
    public String saveTemplate(@RequestBody Template ModelData) {return templateService.saveTemplate(ModelData);}
    @GetMapping("/getAllTemplateData")
    public String getAllTemplate() {
        return templateService.getAllTemplate();
    }
    @GetMapping("/verifyTemplateNameExist/{name}")
    public Boolean verifyTemplateNameExist(@PathVariable String name) {
        return templateService.verifyTemplateNameExist(name);
    }
    @DeleteMapping("/deleteTemplate")
    public String deleteTemplate(@RequestBody String name) {
        return templateService.deleteTemplate(name);
    }
    @GetMapping("/getDataTemplate/{name}")
    public TemplateData getDataTemplate(@PathVariable String name){return templateService.getDataTemplate(name);}

    @PatchMapping("/ModifyTemplateData")
    public String ModifyTemplateData(@RequestBody Template template){return templateService.ModifyTemplateData(template);}

}
