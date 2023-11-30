package prankmail.file;

import java.io.*;
import java.util.*;

import static prankmail.email.EmailMessage.validateEmail;

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
    public static String readJsonFile(String filePath) throws IOException {
        StringBuilder lines = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null)
                lines.append(line);
        }
        catch (IOException e) {
            throw new IOException("\"" + filePath + "\" not found.");
        }

        return lines.toString();
    }

    /**
     * Read a file of email addresses
     *
     * @param filePath the path of the file to read
     * @return a list of lines of email addresses
     * @throws IOException if the file doesn't exist
     */
    public static List<String> readEmailFile(String filePath) throws IOException {
        List<String> emails = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;

            for (int i = 1; (line = reader.readLine()) != null; i++) {
                if (!validateEmail(line)) {
                    System.out.println("\u001B[33mWARNING: " +
                            "Invalid email address at line " + i + ": \"" + line + "\"\u001B[0m");
                    continue;
                }
                emails.add(line);
            }

            if(emails.size() < 2)
                throw new IllegalArgumentException("There must be at least 2 valid email addresses.");

        } catch (IOException e) {
            throw new IOException("\"" + filePath + "\" not found.");
        }

        return emails;
    }
}