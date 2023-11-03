package com.example.packagert.type;

import com.example.packagert.common.DbConnector;

import java.util.List;

public class TypeService {

    private final DbConnector dbConnector;

    public TypeService(final DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public List<TypeEntity> getTypes() {
        return dbConnector.selectAll("SELECT * FROM DbType", TypeEntity.class);
    }
}
