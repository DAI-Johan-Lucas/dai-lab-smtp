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
                    System.err.println("Invalid email address at line " + i + " : \"" + line + "\"");
                    continue;
                }
                emails.add(line);
            }

        } catch (IOException e) {
            throw new IOException("\"" + filePath + "\" not found.");
        }

        return emails;
    }
}