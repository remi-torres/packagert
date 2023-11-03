package com.example.packagert.type;

import com.example.packagert.common.DbConvertion;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeEntity implements DbConvertion<TypeEntity> {

    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty sqliteTranslation = new SimpleStringProperty();
    private final StringProperty javaEntityTranslation = new SimpleStringProperty();

    /* - */

    public TypeEntity() {
        super();
    }

    /* - */

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

    public String getSqliteTranslation() {
        return sqliteTranslation.get();
    }

    public StringProperty sqliteTranslationProperty() {
        return sqliteTranslation;
    }

    public String getJavaEntityTranslation() {
        return javaEntityTranslation.get();
    }

    public StringProperty javaEntityTranslationProperty() {
        return javaEntityTranslation;
    }

    /* - */

    @Override
    public String toString(){
        return this.name.get();
    }

    @Override
    public TypeEntity fromDb(ResultSet resultSet) throws SQLException {
        this.id.set(resultSet.getLong("type_id"));
        this.name.set(resultSet.getString("type_name"));
        this.sqliteTranslation.set(resultSet.getString("type_sqliteTranslation"));
        this.javaEntityTranslation.set(resultSet.getString("type_javaTranslation"));
        return this;
    }
}
