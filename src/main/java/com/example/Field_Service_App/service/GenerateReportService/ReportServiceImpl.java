package com.example.Field_Service_App.service.GenerateReportService;

import com.example.Field_Service_App.model.Template;
import com.example.Field_Service_App.service.ExternalDataBaseService.ExternalDataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;


@Service
public class ReportServiceImpl implements ReportService {

    @Override
    public String generateReportUsingThymleaf(List<Map<String, Object>> results, String code) {
        TemplateEngine templateEngine = new TemplateEngine();
        StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateEngine.setTemplateResolver(templateResolver);
        Context context = new Context();
        context.setVariable("results", results); // Ajouter la liste des résultats au contexte avec la clé "results"
        return templateEngine.process(code , context);
    }

    @Override
public String prepareTemplateCodeSyntax(Template modelData) {
    String html = modelData.getHtmlData();
    String css = modelData.getCssData();
    if (html != null && css != null) {
        String htmlWithCss = "<!DOCTYPE html>" +
                "<html xmlns:th=\"http://www.thymeleaf.org\">" +
                "<head>" +
                "<style>" + css + "</style>" + // Ajout du CSS dans la balise <style>
                "</head>" +
                 html + // Insertion du contenu HTML
                "</html>";
        return htmlWithCss;
    }
    return "null";
}
}
