package com.example.packagert.table;

import com.example.packagert.column.ColumnEntity;
import com.example.packagert.common.DbConvertion;
import com.example.packagert.project.ProjectEntity;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableEntity implements DbConvertion<TableEntity> {

    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<ProjectEntity> project = new SimpleObjectProperty<>();

    private final ListProperty<ColumnEntity> columns = new SimpleListProperty<>();

    public TableEntity() {
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

    public ObservableList<ColumnEntity> getColumns() {
        return columns.get();
    }

    public void setColumns(ObservableList<ColumnEntity> columns) {
        this.columns.set(columns);
    }

    public ListProperty<ColumnEntity> columnsProperty() {
        return columns;
    }

    @Override
    public String toString(){
        return this.name.get();
    }

    @Override
    public TableEntity fromDb(ResultSet resultSet) throws SQLException {
        this.id.set(resultSet.getLong("table_id"));
        this.name.set(resultSet.getString("table_name"));
        this.project.set(new ProjectEntity().fromDb(resultSet));
        return this;
    }
}
