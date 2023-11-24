package com.example.packagert.feature;

import com.example.packagert.column.ColumnEntity;
import com.example.packagert.common.DbConvertion;
import com.example.packagert.project.ProjectEntity;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeatureEntity implements DbConvertion<FeatureEntity> {

    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<FeatureEntity> parent = new SimpleObjectProperty<>();
    private final ObjectProperty<ProjectEntity> project = new SimpleObjectProperty<>();
    private final ObservableList<ColumnEntity> columns = FXCollections.observableArrayList();


    public FeatureEntity() {
        super();
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

    public ProjectEntity getProject() {
        return project.get();
    }

    public ObjectProperty<ProjectEntity> projectProperty() {
        return project;
    }

    public ObservableList<ColumnEntity> getColumns() {
        return columns;
    }

    @Override
    public String toString(){
        return this.name.get();
    }

    @Override
    public FeatureEntity fromDb(ResultSet resultSet) throws SQLException {
        this.id.set(resultSet.getLong("feature_id"));
        this.name.set(resultSet.getString("feature_name"));

        try{
            resultSet.findColumn("parent_feature_id");

            final FeatureEntity parent =  new FeatureEntity();
            parent.id.set(resultSet.getLong("parent_feature_id"));
            parent.name.set(resultSet.getString("parent_name_id"));
            final FeatureEntity grandParent = new FeatureEntity();
            grandParent.id.set(resultSet.getLong("parent_parent_id"));
            this.parent.set(grandParent);
        }catch (SQLException ex){
            if(ex.getMessage().contains("no such column: 'parent_feature_id'")){
                System.out.println("no data for ParentFeatureId");
            }else
                throw new SQLException(ex);
        }

        this.project.set(new ProjectEntity().fromDb(resultSet));
        return this;
    }

}
