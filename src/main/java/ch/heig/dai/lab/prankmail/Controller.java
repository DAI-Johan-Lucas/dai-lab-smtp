package main.java.ch.heig.dai.lab.prankmail;

import main.java.ch.heig.dai.lab.prankmail.config.ConfigReader;
import main.java.ch.heig.dai.lab.prankmail.email.EmailMessage;
import main.java.ch.heig.dai.lab.prankmail.group.Group;

import java.io.*;
import java.util.*;

/**
 * Controller class allows to generate and send prank emails
 */
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

    /**
     * Generate and return a list of email prank groups
     * @param numberOfGroups Number of groups to generate
     * @return List of email prank groups
     */
    public List<Group> generatePrankGroups(int numberOfGroups) {
        List<Group> prankGroups = new ArrayList<>();

        for (int i = 0; i < numberOfGroups; ++i) {
            // Generate a group with random email addresses and a random prank message
            Group group = new Group(getRandomEmails(), getRandomMessage());

            // For every email address in the group except the first one, we forge a message
            List<String> forgedMessages = new ArrayList<>();
            for(int j = 1; j < group.getEmailAddresses().size(); ++j) {
                EmailMessage message = new EmailMessage(
                        group.getEmailAddresses().get(0),
                        group.getEmailAddresses().get(j),
                        group.getPrankMessage()
                );
                forgedMessages.add(message.forge());
            }
            group.addForgedMessages(forgedMessages);
            prankGroups.add(group);
        }
        return prankGroups;
    }

    /**
     * Send the prank emails
     * @param prankGroups List of email prank groups
     * @param smtpServerAddress Address of the SMTP server
     * @param smtpServerPort Port of the SMTP server
     * @throws IOException if there is an error sending the emails
     */
    public void sendPrankEmails(List<Group> prankGroups, String smtpServerAddress, int smtpServerPort) throws IOException {
        // Connect to the SMTP server
        /*
        SMTPClient client = new SMTPClient(smtpServerAddress, smtpServerPort);
        client.connect();

        // Send the prank emails
        for (Group group : prankGroups) {
            // Send the prank message to the first email address in the group
            EmailMessage message = new EmailMessage(
                    group.getEmailAddresses().get(0),
                    group.getEmailAddresses().get(0),
                    group.getPrankMessage()
            );
            client.send(message);

            // Send the forged messages to the other email addresses in the group
            for (String forgedMessage : group.getForgedMessages()) {
                client.send(forgedMessage);
            }
        }

        // Disconnect from the SMTP server
        client.disconnect();
        */
    }
}
