package com.example.Field_Service_App.service.ExcelReportService;

import com.example.Field_Service_App.model.Template;
import com.example.Field_Service_App.service.ExternalDataBaseService.ConnectionData;
import com.example.Field_Service_App.service.ExternalDataBaseService.ExternalDataBaseService;
import com.example.Field_Service_App.service.GenerateReportService.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ExcelReportServiceImpl implements  ExcelReportService{
    ExternalDataBaseService externalDataBaseService;
    ReportService reportService;
    @Override
    public String generateAndDownloadExcel(String processedHtml) {
//        try {
//            // Parse the HTML content
//            Document doc = Jsoup.parse(htmlContent);
//
//            // Create a new Workbook
//            Workbook workbook = new XSSFWorkbook();
//
//            // Find all table elements
//            Elements tables = doc.select("table");
//            for (int i = 0; i < tables.size(); i++) {
//                Element table = tables.get(i);
//                // Create a new sheet for each table
//                Sheet sheet = workbook.createSheet("Sheet" + (i + 1));
//
//                // Process the table rows and cells
//                Elements rows = table.select("tr");
//                int rowNum = 0;
//                for (Element row : rows) {
//                    Row excelRow = sheet.createRow(rowNum++);
//                    Elements cells = row.select("td");
//                    int cellNum = 0;
//                    for (Element cell : cells) {
//                        excelRow.createCell(cellNum++).setCellValue(cell.text());
//                    }
//                }
//            }
//        }
        return "success";
    }
    @Override
    public String exporterReportToExcel(String SQLquery, ConnectionData data, Template modelData) {
        List<Map<String, Object>> results = externalDataBaseService.getDataFromSQLQuery(SQLquery, data);
        String code = reportService.prepareTemplateCodeSyntax(modelData);
        String processedHtml = reportService.generateReportUsingThymleaf(results, code);
        return generateAndDownloadExcel(processedHtml);

    }
}
