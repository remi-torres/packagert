package com.example.packagert.util;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileUtil {

    public static String getResourceFileContent(final String fileName, final Class<?> callerClass) {
        try(InputStream content = callerClass.getResourceAsStream(fileName)){
            if (content == null)
                throw new IllegalArgumentException("file not found! " + fileName);
            return new String(content.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getFileContent(final URL fileUrl) {
       // Path path = Paths.get(fileName);
        try (Scanner scanner = new Scanner(fileUrl.openStream())) {
            final StringBuilder stringBuilder = new StringBuilder();
            while(scanner.hasNextLine())
                stringBuilder.append(scanner.nextLine()).append(System.lineSeparator());
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeInFile(final String fileName, final String content){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static Set<String> getFilesInRepository(File directory) {
            return Stream.of(directory.listFiles())
                    .filter(file -> !file.isDirectory())
                    .map(File::getName)
                    .collect(Collectors.toSet());
    }

}
