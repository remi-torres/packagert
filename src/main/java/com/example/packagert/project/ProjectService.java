package com.example.packagert.project;

import com.example.packagert.common.DbConnector;

import java.util.List;

public class ProjectService {

    private final DbConnector dbConnector;

    public ProjectService(final DbConnector dbConnector){
        this.dbConnector = dbConnector;
    }

    public void addProject(final String projectName, final String projectPath){
        dbConnector.insert("INSERT INTO Project(project_name, project_path) VALUES (?,?)", List.of(projectName, projectPath));
    }

    public List<ProjectEntity> getProjects() {
        return dbConnector.selectAll("SELECT * FROM Project", ProjectEntity.class);
    }
}
