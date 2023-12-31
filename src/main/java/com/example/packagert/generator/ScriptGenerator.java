package com.example.packagert.generator;

public class ScriptGenerator {
//
//    public String generateSqlite(final FeatureEntity table){
//        StringBuilder script = new StringBuilder();
//        script.append(String.format("CREATE TABLE IF NOT EXISTS %s (", table.getName())).append(System.lineSeparator());
//
//        for(ColumnEntity column : table.getColumns()){
//            if(isForeignKey(column.getType()))
//                script.append(String.format(" // TODO : manage %s foreign relationship.", column.getName())).append(System.lineSeparator());
//            else
//                script.append(column.getName()).append(" ").append(column.getType().getMariaDbField()).append(",").append(System.lineSeparator());
//        }
//        // deletion of the last comma
//        if(script.toString().endsWith("," + System.lineSeparator()))
//            script = new StringBuilder(script.substring(0, script.length() - (System.lineSeparator().length() + 1)));
//
//        script.append(System.lineSeparator()).append(");");
//        System.out.println(script);
//        return script.toString();
//    }
//
//    public String generateJavaEntity(final FeatureEntity table){
//
//        StringBuilder script = new StringBuilder();
//        for(ColumnEntity column : table.getColumns()){
//            if(isForeignKey(column.getType()))
//                script.append(String.format(" // TODO : manage %s foreign relationship.", column.getName())).append(System.lineSeparator());
//            else
//                script.append("private").append(" ").append(column.getType().getJavaEntityTranslation()).append(" ").append(column.getName()).append(";").append(System.lineSeparator());
//        }
//        System.out.println(script);
//        return script.toString();
//    }
//
//    public String generateJavaDto(final FeatureEntity table){
//
//        StringBuilder script = new StringBuilder();
//        for(ColumnEntity column : table.getColumns()){
//            if(isForeignKey(column.getType()))
//                script.append(String.format(" // TODO : manage %s foreign relationship.", column.getName())).append(System.lineSeparator());
//            else
//                script.append("private").append(" ").append(column.getType().getJavaEntityTranslation()).append(" ").append(column.getName()).append(";").append(System.lineSeparator());
//        }
//        System.out.println(script);
//        return script.toString();
//    }
//
//    public String generateMappingService(final FeatureEntity table) throws IOException, URISyntaxException {
//
//        String fileContent = FileUtil.getResourceFileContent("template/MappingService.java", ScriptGenerator.class);
//
//        StringBuilder script = new StringBuilder();
//
//        for(ColumnEntity column : table.getColumns()){
//            if(isForeignKey(column.getType()))
//                script.append(String.format(" // TODO : manage %s foreign relationship.", column.getName())).append(System.lineSeparator());
//            else
//                script.append("entity.set").append(StringUtil.capitalize(column.getName())).append("(dto.get").append(StringUtil.capitalize(column.getName())).append("());").append(System.lineSeparator());
//        }
//        fileContent = fileContent.replace("$dtoToEntity$", script);
//
//        script = new StringBuilder();
//        for(ColumnEntity column : table.getColumns()){
//            if(isForeignKey(column.getType()))
//                script.append(String.format(" // TODO : manage %s foreign relationship.", column.getName())).append(System.lineSeparator());
//            else
//                script.append("dto.set").append(StringUtil.capitalize(column.getName())).append("(entity.get").append(StringUtil.capitalize(column.getName())).append("());").append(System.lineSeparator());
//        }
//        fileContent = fileContent.replace("$entityToDto$", script);
//        System.out.println(fileContent);
//
//        return fileContent;
//    }
//
//    private boolean isForeignKey(final TypeEntity type) {
//        return type.getName().equals("ONE_TO_ONE")|| type.getName().equals("MANY_TO_ONE")  || type.getName().equals("ONE_TO_MANY") || type.getName().equals("MANY_TO_MANY");
//    }
}
