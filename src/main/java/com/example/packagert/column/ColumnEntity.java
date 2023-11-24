package com.example.packagert.column;

import com.example.packagert.common.DbConvertion;
import com.example.packagert.feature.FeatureEntity;
import com.example.packagert.generator.typegenerator.TypeGeneratorEnum;
import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ColumnEntity implements DbConvertion<ColumnEntity> {

    private final LongProperty id = new SimpleLongProperty();
    private final ObjectProperty<FeatureEntity> feature = new SimpleObjectProperty<>();
    private final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<TypeGeneratorEnum> type = new SimpleObjectProperty<>();
    private final Map<TgParameterEnum, String> parameters = new HashMap<>();
    private final ColumnParameter columnParameter = new ColumnParameter();


    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public FeatureEntity getFeature() {
        return feature.get();
    }

    public ObjectProperty<FeatureEntity> featureProperty() {
        return feature;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public TypeGeneratorEnum getType() {
        return type.get();
    }

    public ObjectProperty<TypeGeneratorEnum> typeProperty() {
        return type;
    }

    public ColumnParameter getParameters(){
        return columnParameter;
    }
    public String get(final TgParameterEnum key){
        return parameters.get(key);
    }

    public void set(final TgParameterEnum key, final String value){
        parameters.put(key, value);
    }

    public void set(final TgParameterEnum key, final Integer value){
        parameters.put(key, value.toString());
    }

    public void set(final TgParameterEnum key, final Boolean value){
        parameters.put(key, value.toString());
    }

    @Override
    public ColumnEntity fromDb(ResultSet resultSet) throws SQLException {
        this.id.set(resultSet.getLong("column_id"));
        this.feature.set(new FeatureEntity().fromDb(resultSet));
        this.name.set(resultSet.getString("column_name"));
//        this.type.set(new TypeEntity().fromDb(resultSet));
        return this;
    }
}
