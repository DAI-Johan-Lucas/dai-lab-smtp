package ch.heig.dai.lab.prankmail.file;

import java.io.*;

import static ch.heig.dai.lab.prankmail.email.EmailMessage.validateEmail;

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
    public static String readLines(String filePath) throws IOException {
        StringBuilder lines = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null)
                lines.append(line);
        }
        catch (IOException e) {
            throw new IOException("Error reading file " + filePath);
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
    public static String readEmailFile(String filePath) throws IOException {
        StringBuilder emails = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                validateEmail(line);
                emails.append(line);
            }
        }
        catch (IOException e) {
            throw new IOException("Error reading email file " + filePath);
        }

        return emails.toString();
    }
}