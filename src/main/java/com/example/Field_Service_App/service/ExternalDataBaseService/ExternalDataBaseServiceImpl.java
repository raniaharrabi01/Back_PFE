package com.example.Field_Service_App.service.ExternalDataBaseService;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExternalDataBaseServiceImpl implements ExternalDataBaseService {


    @Override
    public DataSource createDataSource(ConnectionData data) {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        try {
            dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
            String url = String.format("jdbc:mysql://"+data.getHost()+":"+data.getPort()+"/"+data.getBase_name());
            dataSourceBuilder.url(url);
            dataSourceBuilder.username(data.getUsername());
            dataSourceBuilder.password(data.getPassword());
            return dataSourceBuilder.build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public JdbcTemplate createJdbcTemplate(ConnectionData data) {
        return new JdbcTemplate(createDataSource(data));
    }

    @Override
    public Map<String, List<String>> getAllTablesAndColumnsName(ConnectionData data) {
        JdbcTemplate jdbcTemplateTest = createJdbcTemplate(data);
        List<String> tableNames = jdbcTemplateTest.queryForList("SELECT table_name FROM information_schema.tables WHERE table_schema = ?", new Object[]{data.getBase_name()}, String.class);
        Map<String, List<String>> tablesAndColumns = new HashMap<>();
        for (String tableName : tableNames) {
            List<String> columns = jdbcTemplateTest.queryForList("SELECT column_name FROM information_schema.columns WHERE table_name = ?", new Object[]{tableName}, String.class);
            tablesAndColumns.put(tableName, columns);
        }
        return tablesAndColumns;
    }


    public List<Map<String, Object>> getDataFromSQLQuery(String requeteSQL, ConnectionData data) {
        try {
            JdbcTemplate jdbcTemplate = createJdbcTemplate(data);
            return jdbcTemplate.queryForList(requeteSQL);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Collections.emptyList(); // Ou une autre valeur par défaut appropriée
        }
    }

    @Override
    public List<Map<String, Object>> getDataFromSelectColumnsList(String tablename, List<String> colonnename, ConnectionData data) {
        JdbcTemplate jdbcTemplate = createJdbcTemplate(data);
        // Construire la partie SELECT de la requête SQL en utilisant les noms de colonnes de la liste colonnename
        String selectColumns = String.join(",", colonnename);
        // Construire la requête SQL avec les noms de colonnes et le nom de la table spécifiée
        String sql = "SELECT " + selectColumns + " FROM " + tablename;
        return jdbcTemplate.queryForList(sql);
    }


}
