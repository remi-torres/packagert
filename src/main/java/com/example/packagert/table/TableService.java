package com.example.packagert.table;

import com.example.packagert.common.DbConnector;

import java.util.List;

public class TableService {

    private final DbConnector dbConnector;

    public TableService(final DbConnector dbConnector){
        this.dbConnector = dbConnector;
    }

    public long addTable(final long projectId, final String tableName){
        return dbConnector.insert("INSERT INTO DbTable(table_projectId, table_name) VALUES (?,?)", List.of(projectId, tableName));
    }

    public List<TableEntity> getTables(final long projectId){
        final String query = "SELECT * FROM DbTable t INNER JOIN DbProject p ON p.project_id = t.table_projectId WHERE t.table_projectId = ?";
        return dbConnector.selectAll(query, List.of(projectId), TableEntity.class);
    }

}
