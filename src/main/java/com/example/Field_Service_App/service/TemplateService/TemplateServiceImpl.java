package com.example.Field_Service_App.service.TemplateService;
import com.example.Field_Service_App.model.Template;
import com.example.Field_Service_App.repository.TemplateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    TemplateRepository templateRepository;

    @Override
    public String saveTemplate(Template modelData) {
        Template entity = new Template();
        entity.setHtmlData(modelData.getHtmlData());
        entity.setCssData(modelData.getCssData());
        entity.setModelName(modelData.getModelName());
        entity.setContext(modelData.getContext());
        // Obtenez la date actuelle
        Date currentDate = new Date();
        entity.setSaveDate(currentDate);
        templateRepository.save(entity);
        return ("Le modèle a été enregistrer avec succès.");
    }

    @Override
    public String getAllTemplate() {
        List<Template> modelDataList = templateRepository.findAll();
        List<Map<String, Object>> modelDataMapList = modelDataList.stream()
                .map(modelData -> {
                    Map<String, Object> modelMap = new HashMap<>();
                    modelMap.put("name", modelData.getModelName());
                    modelMap.put("save_date", formatDate(modelData.getSaveDate()));
                    if (modelData.getModifyDate() != null) {
                        modelMap.put("modify_date", formatDate(modelData.getModifyDate()));
                    }
                    return modelMap;
                })
                .collect(Collectors.toList());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(modelDataMapList);
        } catch (Exception e) {
            e.printStackTrace();
            return "erreur";
        }
    }
    // Fonction pour formater la date au format souhaité

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // Définissez le format de date souhaité
        return sdf.format(date);
    }

    @Override
    public String deleteTemplate(String name) {
        Optional<Template> model = templateRepository.findByname(name);
        if (model.isPresent()) {
            Template entity = model.get();
            templateRepository.delete(entity);
            return ("Le modèle a été supprimé avec succès.");
        } else {
            return ("Le modèle n'a pas été trouvé.");
        }
    }

    @Override
    public Boolean verifyTemplateNameExist(String name) {
        Optional<Template> model = templateRepository.findByname(name);
        if (model.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public TemplateData getDataTemplate(String name) {
        Optional<Template> model = templateRepository.findByname(name);
        if (model.isPresent()) {
            TemplateData originalTemplate = new TemplateData();
            originalTemplate.setId(model.get().getId());
            originalTemplate.setHtmlData(model.get().getHtmlData());
            originalTemplate.setCssData(model.get().getCssData());
            return originalTemplate;
        } else {
            return null;
        }
    }

    @Override
    public String ModifyTemplateData(Template template) {
        Template entity = templateRepository.getReferenceById(template.getId());
        entity.setModelName(template.getModelName());
        entity.setHtmlData(template.getHtmlData());
        entity.setCssData(template.getCssData());
        entity.setContext(template.getContext());
        // Obtenez la date actuelle
        Date currentDate = new Date();
        entity.setModifyDate(currentDate);
        templateRepository.save(entity);
        return ("Le modèle a été enregistrer avec succès.");
    }
}