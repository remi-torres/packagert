package com.example.packagert.generator;

import com.example.packagert.column.ColumnEntity;
import com.example.packagert.feature.FeatureEntity;
import com.example.packagert.generator.typegenerator.TypeGeneratorEnum;
import com.example.packagert.util.StringUtil;

import static com.example.packagert.generator.typegenerator.TypeGenerator.idSuffix;

public class MariadbGenerator {

    public String generateSqlite(final FeatureEntity feature){
        StringBuilder script = new StringBuilder();
        StringBuilder constraints = new StringBuilder();
        StringBuilder otherScript = new StringBuilder();
        script.append(String.format("CREATE TABLE IF NOT EXISTS %s%s (", StringUtil.capitalize(feature.getProject().getName()), StringUtil.capitalize(feature.getName()))).append(System.lineSeparator());

        for(ColumnEntity column : feature.getColumns()){
            if(TypeGeneratorEnum.MANY_TO_MANY.equals(column.getType())){
             otherScript.append(createOtherScript(feature, column));
            //script.append(String.format(" // TODO : manage %s foreign relationship,", column.getName())).append(System.lineSeparator());
            } else {
                script.append(column.getType().getType().getMariadbField(column)).append(",").append(System.lineSeparator());
                String constraint = column.getType().getType().getMariadbConstraint(column);
                if(!constraint.isEmpty())
                    constraints.append(constraint).append(",").append(System.lineSeparator());
            }
        }

        // deletion of the last comma
        if(constraints.isEmpty() &&  script.toString().endsWith("," + System.lineSeparator()))
            script = new StringBuilder(script.substring(0, script.length() - (System.lineSeparator().length() + 1)));

        if(constraints.toString().endsWith("," + System.lineSeparator()))
            constraints = new StringBuilder(constraints.substring(0, constraints.length() - (System.lineSeparator().length() + 1)));

        script.append(constraints).append(System.lineSeparator()).append(");");
        if(! otherScript.isEmpty())
            script.append(System.lineSeparator()).append(System.lineSeparator()).append(otherScript);
        System.out.println(script);
        return script.toString();
    }

    private StringBuilder createOtherScript(final FeatureEntity feature, final ColumnEntity column) {
        final StringBuilder otherScript = new StringBuilder();
        final FeatureEntity linkedEntity = column.getParameters().getLinkedEntity();
        final String tableName = StringUtil.capitalize(feature.getName()) + StringUtil.capitalize(linkedEntity.getName());
        final String key1Name = feature.getName() + idSuffix;
        final String key2Name = linkedEntity.getName().toLowerCase() + idSuffix;
        otherScript.append(String.format("CREATE TABLE IF NOT EXISTS %s%s(", StringUtil.capitalize(feature.getProject().getName()), tableName)).append(System.lineSeparator());
        otherScript.append(String.format("%s BIGINT(20) NOT NULL,", key1Name)).append(System.lineSeparator());
        otherScript.append(String.format("%s BIGINT(20) NOT NULL,", key2Name)).append(System.lineSeparator());
        otherScript.append(String.format("PRIMARY KEY (%s,%s),", key1Name, key2Name)).append(System.lineSeparator());
        otherScript.append(String.format("CONSTRAINT fk%s%s FOREIGN KEY (%s) REFERENCES %s (id)%s,", tableName, StringUtil.capitalize(feature.getName()), key1Name, StringUtil.capitalize(feature.getProject().getName()) + StringUtil.capitalize(feature.getName()), column.getParameters().getCascadeDeletionMariadb())).append(System.lineSeparator());
        otherScript.append(String.format("CONSTRAINT fk%s%s FOREIGN KEY (%s) REFERENCES %s (id)%s", tableName, StringUtil.capitalize(linkedEntity.getProject().getName()), key2Name, StringUtil.capitalize(feature.getProject().getName()) + StringUtil.capitalize(linkedEntity.getName()), column.getParameters().getCascadeDeletionMariadb())).append(System.lineSeparator());
        otherScript.append(");").append(System.lineSeparator());
                return otherScript;
    }
}
