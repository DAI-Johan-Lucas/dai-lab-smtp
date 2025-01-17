package prankmail;

import prankmail.email.EmailMessage;
import prankmail.email.Message;
import prankmail.group.Group;
import prankmail.smtp.SMTPClient;
import com.google.gson.Gson;

import java.io.*;
import java.util.*;

import static java.util.Collections.shuffle;
import static prankmail.file.FileReader.*;

/**
 * Controller class allows to generate and send prank emails
 */
public class Controller {

    /**
     * List of email addresses from the file
     */
    private List<String> emailAddresses = new ArrayList<>();

    /**
     * List of messages from the file
     */
    private List<Message> messages;

    /**
     * Constructor
     *
     * @param victimsFilePath  Path to the file containing email addresses of victims
     * @param messagesFilePath Path to the file containing email messages
     */
    public Controller(String victimsFilePath, String messagesFilePath) {
        try {
            Gson gson = new Gson();
            this.emailAddresses = readEmailFile(victimsFilePath);
            this.messages = List.of(gson.fromJson(readJsonFile(messagesFilePath), Message[].class));
        } catch (IOException e) {
            System.err.println("Error reading messages: " + e.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.println("Error reading emails: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Get a random sublist of email addresses
     *
     * @return Random sublist of email addresses
     */
    private List<String> getRandomEmails() {
        var emails = new ArrayList<>(emailAddresses);

        // Shuffle the list of email addresses and return 2 to 5 of them
        shuffle(emails);
        int minGroupSize = 2;
        int maxGroupSize = Math.min(5, emails.size());
        int groupSize = new Random().nextInt(maxGroupSize - minGroupSize + 1) + minGroupSize;
        return emails.subList(0, groupSize);
    }

    /**
     * Get a random prank message
     *
     * @return Random prank message
     */
    private Message getRandomMessage() {
        var msg = new ArrayList<>(messages);

        // Shuffle the list of prank message and return one
        shuffle(msg);
        return msg.get(0);
    }

    /**
     * Generate and return a list of prank groups
     *
     * @param numberOfGroups Number of groups to generate
     * @return List of prank groups
     */
    public List<Group> generatePrankGroups(int numberOfGroups) {
        List<Group> prankGroups = new ArrayList<>();

        try {
            if (numberOfGroups < 1 || numberOfGroups > 20)
                throw new IllegalArgumentException("Number of groups must between 1 and 20.");

            for (int i = 0; i < numberOfGroups; ++i) {
                // Generate a group with random email addresses and a random prank message
                Group group = new Group(getRandomEmails(), getRandomMessage());

                //We forge a message for the group
                StringBuilder to = new StringBuilder();
                for (int j = 1; j < group.getEmailAddresses().size(); ++j) {
                    String email = group.getEmailAddresses().get(j);
                    to.append(EmailMessage.getName(email));
                    to.append("<").append(email).append(">");
                    to.append(", ");
                }
                to.delete(to.length()-2, to.length());
                EmailMessage message = new EmailMessage(
                        group.getEmailAddresses().get(0),
                        to.toString(),
                        group.getPrankMessage()
                );
                group.addForgedMessages(message.forge());
                prankGroups.add(group);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid number of groups: " + e.getMessage());
            System.exit(1);
        }
        return prankGroups;
    }

    /**
     * Send the prank emails
     *
     * @param prankGroups   List of prank groups
     * @param serverAddress SMTP server address
     * @param serverPort    SMTP server port
     */
    public void sendPrankEmails(List<Group> prankGroups, String serverAddress, int serverPort) {
        for (int i = 0; i < prankGroups.size(); ++i) {
            System.out.print("Prank launched on group #" + (i + 1) + " : ");
            SMTPClient client = new SMTPClient(prankGroups.get(i), serverAddress, serverPort);
            client.run();
            System.out.println("\u001B[92mSuccess!\u001B[0m");
        }
    }
}
