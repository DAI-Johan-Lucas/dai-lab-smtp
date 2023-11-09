package main.java.ch.heig.dai.lab.prankmail.group;

import main.java.ch.heig.dai.lab.prankmail.email.EmailMessage;

import java.util.*;

/**
 * Group class allows to create a group of email addresses and a forged email message
 */
public class Group {
    private List<String> emailAddresses;
    private String prankMessage;
    private List<String> forgedMessages;

    /**
     * Constructor
     * @param emailAddresses List of email addresses in the group
     * @param prankMessage prank message to send
     */
    public Group(List<String> emailAddresses, String prankMessage) {
        this.emailAddresses = new ArrayList<>(emailAddresses);
        this.prankMessage = prankMessage;
    }

    /**
     * Get the list of email addresses in the group
     * @return List of email addresses
     */
    public List<String> getEmailAddresses() {
        return new ArrayList<>(emailAddresses);
    }

    /**
     * Get the forged email message for the group
     * @return Forged email message
     */
    public String getPrankMessage() {
        return prankMessage;
    }

    /**
     * Get the forged email messages for the group
     * @return Forged email messages
     */
    public List<String> getForgedMessages() {
        return forgedMessages;
    }

    /**
     * Add a forged email message to the group
     * @param forgedMessages Forged email message
     */
    public void addForgedMessages(List<String> forgedMessages) {
        this.forgedMessages = forgedMessages;
    }
}
