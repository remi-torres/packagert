package com.example.packagert.project;

import com.example.packagert.common.DbConnector;

import java.util.List;

public class ProjectService {

    private final DbConnector dbConnector;

    public ProjectService(final DbConnector dbConnector){
        this.dbConnector = dbConnector;
    }

    public void addProject(final String projectName){
        dbConnector.insert("INSERT INTO DbProject(project_name) VALUES (?)", List.of(projectName));
    }

    public List<ProjectEntity> getProjects() {
        return dbConnector.selectAll("SELECT * FROM DbProject", ProjectEntity.class);
    }
}
