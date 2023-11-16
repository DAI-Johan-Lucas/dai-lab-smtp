package ch.heig.dai.lab.prankmail.file;

import java.io.*;
import java.util.*;

/**
 * ConfigReader class allows to read a file line by line
 */
public class FileReader {

    /**
     * Read a file line by line
     * @param filePath the path of the file to read
     * @return a list of lines
     * @throws IOException if the file doesn't exist
     */
    public static List<String> readLines(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null)
                lines.add(line);
        }
        catch (IOException e) {
            throw new IOException("Error reading file " + filePath);
        }

        return lines;
    }
}