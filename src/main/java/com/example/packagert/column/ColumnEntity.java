package com.example.packagert.column;

import com.example.packagert.common.DbConvertion;
import com.example.packagert.table.TableEntity;
import com.example.packagert.type.TypeEntity;
import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColumnEntity implements DbConvertion<ColumnEntity> {

    private final LongProperty id = new SimpleLongProperty();
    private final ObjectProperty<TableEntity> table = new SimpleObjectProperty<>();
    private final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<TypeEntity> type = new SimpleObjectProperty<>();

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public TableEntity getTable() {
        return table.get();
    }

    public ObjectProperty<TableEntity> tableProperty() {
        return table;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public TypeEntity getType() {
        return type.get();
    }

    public ObjectProperty<TypeEntity> typeProperty() {
        return type;
    }

    @Override
    public ColumnEntity fromDb(ResultSet resultSet) throws SQLException {
        this.id.set(resultSet.getLong("column_id"));
        this.table.set(new TableEntity().fromDb(resultSet));
        this.name.set(resultSet.getString("column_name"));
        this.type.set(new TypeEntity().fromDb(resultSet));
        return this;
    }
}
