package com.example.packagert.packager;

import com.example.packagert.util.FileUtil;
import com.example.packagert.util.StringUtil;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class PackagerService {

    private final String parentPackage;
    private final Map<String, String> tags;


    public PackagerService(final String copyParentPackage, final Map<String, String> tags){
        this.parentPackage = copyParentPackage;
        this.tags = tags;
    }

    public void createPackage(final List<String> templateFileNames) {
        for(String template : templateFileNames)
            transformTemplate(template);
    }

    private void transformTemplate(String template) {
        System.out.println("template : " + template);
        final String[] templateData = template.split("-");
        final String copyPackageName = templateData[0].trim();
        final String copyFileName = templateData[1].trim();
        final File copyPackage = new File(parentPackage + File.separator + copyPackageName );
        if(! copyPackage.exists())
            if(!copyPackage.mkdirs())
                throw new RuntimeException(String.format("Folder %s not created", copyPackageName));

        final String copyPath = parentPackage + File.separator + copyPackageName + File.separator + copyFileName;
        if(!new File(copyPath).exists()) {
            createNewFile(template, copyPath);
        }
    }

    private void createNewFile(final String template, final String copyPath) {

        final URL originalFileName = getClass().getResource("/com/example/packagert/template/" + template) ; //+ File.separator
        final String content = FileUtil.getFileContent(originalFileName);
        final String newContent = replaceTags(content, tags);

        final String newCopyPath = replaceTags(copyPath, tags);
        FileUtil.writeInFile(newCopyPath, newContent);
    }


    private String replaceTags(final String content, final Map<String, String> tags) {
        String newContent = content;
        for(String tag : tags.keySet()){
            System.out.println("tag : " + tag);

            while(newContent.contains("$" + tag+ "$")){
                System.out.println("-tag-");
                newContent = newContent.replace("$" + tag+ "$", tags.get(tag));
            }

            while(newContent.contains("$" + StringUtil.capitalize(tag) + "$" )){
                System.out.println("-Tag-");
                newContent = newContent.replace("$" + StringUtil.capitalize(tag) + "$", StringUtil.capitalize(tags.get(tag)));
            }

            while(newContent.contains("$" + tag.toUpperCase() + "$" )){
                System.out.println("-TAG-");
                newContent = newContent.replace("$" + tag.toUpperCase() + "$", tags.get(tag).toUpperCase());
            }

            System.out.println("- end of tag -");
        }
        return newContent;
    }

}
