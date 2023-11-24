package com.example.packagert.generator.typegenerator;

import com.example.packagert.column.ColumnEntity;
import com.example.packagert.column.TgParameterEnum;

import java.util.Map;

public class TgId implements TypeGenerator {

    private String typeName = "Id";

    public String getTypeName(){
        return typeName;
    }

    @Override
    public Map<TgParameterEnum, Boolean> getAvailableParameters() {
        availableParameters.put(TgParameterEnum.NULLABLE, isOptional);
        return availableParameters;
    }

    @Override
    public String getMariadbField(final ColumnEntity column){
        return String.format( "%s BIGINT(20) PRIMARY KEY AUTOINCREMENT", column.getName());
    }
}
