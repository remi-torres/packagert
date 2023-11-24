package com.example.packagert.generator.typegenerator;

import com.example.packagert.column.ColumnEntity;
import com.example.packagert.column.TgParameterEnum;

import java.util.Map;

public class TgString implements TypeGenerator {

    public Map<TgParameterEnum, Boolean> getAvailableParameters(){
        availableParameters.put(TgParameterEnum.SIZE_MAX, isMandatory);
        availableParameters.put(TgParameterEnum.NULLABLE, isOptional);
        return availableParameters;
    }

    @Override
    public String getMariadbField(final ColumnEntity column) {
        return String.format("%s VARCHAR(%s)%s", column.getName(), column.get(TgParameterEnum.SIZE_MAX), getNotNull(column.get(TgParameterEnum.NULLABLE))) ;
    }
}
