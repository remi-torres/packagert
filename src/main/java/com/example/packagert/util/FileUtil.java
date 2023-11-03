package com.example.packagert.util;

import com.example.packagert.Main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;


public class FileUtil {

    public static String getResourceFileContent(Class<?> callerClass, final String fileName ) throws IOException, URISyntaxException {

        final URL resource = Main.class.getResource(fileName);

        if (resource == null)
            throw new IllegalArgumentException("file not found! " + fileName);

        //File file = new File(resource.getFile());
        final File file = new File(resource.toURI());

        final String content = Files.readString(file.toPath(), StandardCharsets.UTF_8);
        System.out.println(content);
return content;

    }

}
