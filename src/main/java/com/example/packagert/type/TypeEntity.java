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
    private final StringProperty mariaDbField = new SimpleStringProperty();
    private final StringProperty mariaDbConstraint = new SimpleStringProperty();
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

    public String getMariaDbField() {
        return mariaDbField.get();
    }

    public StringProperty mariaDbFieldProperty() {
        return mariaDbField;
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
        this.mariaDbField.set(resultSet.getString("type_mariadbField"));
        this.mariaDbConstraint.set(resultSet.getString("type_mariadbConstraint"));
        //this.javaEntityTranslation.set(resultSet.getString("type_javaTranslation"));
        return this;
    }
}
