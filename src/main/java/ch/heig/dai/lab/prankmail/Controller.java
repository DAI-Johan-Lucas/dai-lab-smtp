package main.java.ch.heig.dai.lab.prankmail;

import main.java.ch.heig.dai.lab.prankmail.config.ConfigReader;
import main.java.ch.heig.dai.lab.prankmail.email.EmailMessage;

import java.io.*;
import java.util.*;
public class Controller {

    private List<String> emailAddresses;
    private List<String> messages;

    /**
     * Constructor
     * @param victimsFilePath Path to the file containing email addresses of victims
     * @param messagesFilePath Path to the file containing email messages
     * @throws IOException if there is an error reading the configuration files
     */
    public Controller(String victimsFilePath, String messagesFilePath) throws IOException {
        this.emailAddresses = ConfigReader.readLines(victimsFilePath);
        this.messages = ConfigReader.readLines(messagesFilePath);
    }

    /**
     * Generate and return a list of email prank groups
     * @param numberOfGroups Number of groups to generate
     * @return List of email prank groups
     */
    public List<Group> generatePrankGroups(int numberOfGroups) {
        List<Group> prankGroups = new ArrayList<>();

        // For every groups
        for (int i = 0; i < numberOfGroups; i++) {
            // We get a random sublist of email addresses
            List<String> groupEmails = getRandomEmails();
            // And a random prank message
            String message = getRandomMessage();
            // And for every email address in the group
            for (String email : groupEmails) {
                // We have to forge a message with the sender (first email) and all the other recipients
                // TODO
            }
        }

        return prankGroups;
    }

    /**
     * Get a random sublist of email addresses
     * @return Random sublist of email addresses
     */
    private List<String> getRandomEmails() {
        // Shuffle the list of email addresses and pick 2 to 5 of them
        Collections.shuffle(emailAddresses);
        int groupSize = new Random().nextInt(4) + 2;
        return emailAddresses.subList(0, groupSize);
    }

    /**
     * Get a random prank message
     * @return Random prank message
     */
    private String getRandomMessage() {
        Collections.shuffle(messages);
        return messages.get(0);
    }

    // Other methods for sending pranks, connecting to SMTP server, etc.
}
