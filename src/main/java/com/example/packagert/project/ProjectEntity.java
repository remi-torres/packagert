package com.example.packagert.project;

import com.example.packagert.common.DbConvertion;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectEntity implements DbConvertion<ProjectEntity> {

    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty name = new SimpleStringProperty();

    public ProjectEntity() {
        super();
    }

    public ProjectEntity(final long id, final String name) {
        this.id.set(id);
        this.name.set(name);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    @Override
    public String toString(){
        return this.name.get();
    }

    @Override
    public ProjectEntity fromDb(ResultSet resultSet) throws SQLException {
        this.id.set(resultSet.getLong("project_id"));
        this.name.set(resultSet.getString("project_name"));
        return this;
    }

}
