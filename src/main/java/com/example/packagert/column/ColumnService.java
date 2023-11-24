package com.example.packagert.column;

import com.example.packagert.common.DbConnector;

import java.util.List;

public class ColumnService {

    private final DbConnector dbConnector;

    public ColumnService(final DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public long addColumn(final long tableId, String columnName, String typeEnum) {
        return dbConnector.insert("INSERT INTO DbColumn(column_tableId, column_name, column_typeId) VALUES (?,?,?)", List.of(tableId, columnName, typeEnum));
    }

    public List<ColumnEntity> getColumns(long tableId) {
        final String query = """
                SELECT * FROM DbColumn c
                INNER JOIN DbTable t ON t.table_id = c.column_tableId
                INNER JOIN DbProject p ON p.project_id = t.table_projectId
                INNER JOIN DbType y ON y.type_id = c.column_typeId
                WHERE c.column_tableId = ?""";
        return dbConnector.selectAll(query, List.of(tableId), ColumnEntity.class);
    }
}
