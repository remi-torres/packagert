package com.example.packagert.generator.typegenerator;

import com.example.packagert.column.ColumnEntity;
import com.example.packagert.column.TgParameterEnum;

import java.util.Map;

public class TgManyToMany implements TypeGenerator {

    @Override
    public Map<TgParameterEnum, Boolean> getAvailableParameters() {
        availableParameters.put(TgParameterEnum.LINKED_FEATURE, isMandatory);
        availableParameters.put(TgParameterEnum.CASCADE_DELETION, isOptional);
        availableParameters.put(TgParameterEnum.NULLABLE, isOptional);
        return availableParameters;
    }

    @Override
    public String getMariadbField(final ColumnEntity column) {
        return "TODO";
    }
}
