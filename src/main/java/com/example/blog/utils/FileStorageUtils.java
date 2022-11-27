package com.example.blog.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import org.springframework.stereotype.Component;


@Component
public class FileStorageUtils {
    public String getBase64FileFormFilePath(String filePath) throws IOException{
        File readingFile = new  File(filePath);

        if(readingFile.exists()){
            byte [] bytes =Files.readAllBytes(readingFile.toPath());
            String base64String = Base64.getEncoder().encodeToString(bytes);
            return "data:image/jpeg;base64," +base64String;
        }else{
            System.out.println("File not found");
            return null;
        }
    }
}
