package com.example.Field_Service_App.service.ExternalDataBaseService;

import com.example.Field_Service_App.service.ExternalDataBaseService.ConnectionData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Service
public interface ExternalDataBaseService {
    public DataSource createDataSource(ConnectionData Data);
    public Map<String, List<String>> getAllTablesAndColumnsName(ConnectionData data);
    public List<Map<String, Object>> getDataFromSQLQuery(String Sql_query, ConnectionData data);
    public List<Map<String, Object>> getDataFromSelectColumnsList(String table_name, List<String> columns_name, ConnectionData data);
    }