package com.example.Field_Service_App.service.PDFReportService;

import com.example.Field_Service_App.model.Template;
import com.example.Field_Service_App.service.CSVFileService.CSVFileService;
import com.example.Field_Service_App.service.ExternalDataBaseService.ConnectionData;
import com.example.Field_Service_App.service.ExternalDataBaseService.ExternalDataBaseService;
import com.example.Field_Service_App.service.GenerateReportService.ReportService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class PDFReportServiceImpl implements PDFReportService{
    @Autowired
    ExternalDataBaseService externalDataBaseService;
    @Autowired
    ReportService reportService;
    CSVFileService csvFileService;
    @Override
    public String exportHtmlCodeTodPdfFile(String processedHtml) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PdfWriter pdfwriter = new PdfWriter(byteArrayOutputStream);
            DefaultFontProvider defaultFont = new DefaultFontProvider(false, true, false);
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(defaultFont);
            HtmlConverter.convertToPdf(processedHtml.toString(), pdfwriter, converterProperties);
            // Chemin complet du fichier PDF dans le répertoire de téléchargement de l'utilisateur
            String userDownloadsDir = System.getProperty("user.home") + File.separator + "Downloads";
            String filePath = userDownloadsDir + File.separator + "rapport.pdf";
            FileOutputStream fout = new FileOutputStream(filePath);
            byteArrayOutputStream.writeTo(fout);
            byteArrayOutputStream.close();
            byteArrayOutputStream.flush();
            fout.close();
            return "success";
        } catch(Exception ex) {
            ex.printStackTrace(); // Ajoutez une gestion appropriée des erreurs
        }
        return null;
    }
    @Override
    public String exportPDFReportFromSQLquery(String SQLquery, ConnectionData data, Template modelData) {
        // Récupérer les données du rapport en appelant la fonction du service
        List<Map<String, Object>> results = externalDataBaseService.getDataFromSQLQuery(SQLquery, data);
        return ExportPDFReport(modelData,results);
    }
    @Override
    public String ExportPDFReport(Template modelData, List<Map<String, Object>> results ) {
        String code = reportService.prepareTemplateCodeSyntax(modelData);
        String processedHtml = reportService.generateReportUsingThymleaf(results, code);
        return exportHtmlCodeTodPdfFile(processedHtml);
    }

    @Override
    public String exportPDFReportFromSelectColumns(String tablename, ConnectionData data, Template modelData ,List<String> colonnename) {
        // Récupérer les données du rapport en appelant la fonction du service
        List<Map<String, Object>> results = externalDataBaseService.getDataFromSelectColumnsList(tablename, colonnename, data);
        return ExportPDFReport(modelData,results);
    }

    @Override
    public String exportPDFReportFromCSVFile(MultipartFile file, Template modelData) {
        try {
            // Récupérer les données du rapport en appelant la fonction du service
            List<Map<String, Object>> results = csvFileService.getDataFromFile(file);
            return ExportPDFReport(modelData,results);
        } catch (IOException | CsvException e) {
            e.printStackTrace(); // Log the exception
            return "Error generating PDF: " + e.getMessage(); // Return an error message
        }
    }
}
