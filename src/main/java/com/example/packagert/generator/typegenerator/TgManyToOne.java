package com.example.packagert.generator.typegenerator;

import com.example.packagert.column.ColumnEntity;
import com.example.packagert.column.TgParameterEnum;
import com.example.packagert.util.StringUtil;

import java.util.Map;

public class TgManyToOne implements TypeGenerator {

    @Override
    public Map<TgParameterEnum, Boolean> getAvailableParameters() {
        availableParameters.put(TgParameterEnum.LINKED_FEATURE, isMandatory);
        availableParameters.put(TgParameterEnum.CASCADE_DELETION, isOptional);
        availableParameters.put(TgParameterEnum.NULLABLE, isOptional);
        return availableParameters;
    }
    @Override
    public String getMariadbField(final ColumnEntity column) {
        return String.format("%s%s BIGINT(20)%s", column.getName(), idSuffix, getNotNull(column.get(TgParameterEnum.NULLABLE)));
    }

    @Override
    public String getMariadbConstraint(final ColumnEntity column){
        return String.format("CONSTRAINT fk%s%s FOREIGN KEY (%s%s) REFERENCES %s (id)%s", StringUtil.capitalize(column.getFeature().getName()), StringUtil.capitalize(column.getName()), column.getName(), idSuffix, column.get(TgParameterEnum.LINKED_FEATURE), getOnDeleteCascade(column.get(TgParameterEnum.CASCADE_DELETION)));
    }

}
