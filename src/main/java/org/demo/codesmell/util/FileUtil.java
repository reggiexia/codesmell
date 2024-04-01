package org.demo.codesmell.util;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.FileReader;

@Log4j2
public class FileUtil {

    public static String readFile(String filePath) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void writeFile(String filePath, String content) {

    }
}
