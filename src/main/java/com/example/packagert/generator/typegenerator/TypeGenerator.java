package com.example.packagert.generator.typegenerator;

import com.example.packagert.column.ColumnEntity;
import com.example.packagert.column.TgParameterEnum;

import java.util.HashMap;
import java.util.Map;

public interface TypeGenerator {

    String idSuffix = "Id";
    boolean isMandatory = true;
    boolean isOptional = false;

    Map<TgParameterEnum, Boolean> availableParameters = new HashMap<>();

    Map<TgParameterEnum, Boolean> getAvailableParameters();

    // database
    String getMariadbField(final ColumnEntity column);

    default String getMariadbConstraint(ColumnEntity column) {
        return "";
    }

    default String getNotNull(final String value){
        if(value != null && ! Boolean.parseBoolean(value))
            return " NOT NULL";
        return "";
    }

    default String getOnDeleteCascade(final String value) {
        if(Boolean.parseBoolean(value))
            return " ON DELETE CASCADE";
        return "";
    }



}
