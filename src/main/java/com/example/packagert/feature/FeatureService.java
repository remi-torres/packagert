package com.example.packagert.feature;

import com.example.packagert.common.DbConnector;

import java.util.List;

public class FeatureService {

    private final DbConnector dbConnector;

    public FeatureService(final DbConnector dbConnector){
        this.dbConnector = dbConnector;
    }

    public long addFeature(final long projectId, final String tableName){
        return dbConnector.insert("INSERT INTO Feature(feature_project_id, feature_name) VALUES (?,?)", List.of(projectId, tableName));
    }

    public long addFeature(final long projectId, final String tableName, final long parentId){
        return dbConnector.insert("INSERT INTO Feature(feature_project_id, feature_name, feature_parent_id) VALUES (?,?,?)", List.of(projectId, tableName, parentId));
    }

    public List<FeatureEntity> getFeatures(final long projectId){
        final String query = "SELECT * FROM Feature f INNER JOIN Project p ON p.project_id = f.feature_project_id WHERE f.feature_project_id = ?";
        return dbConnector.selectAll(query, List.of(projectId), FeatureEntity.class);
    }

}
