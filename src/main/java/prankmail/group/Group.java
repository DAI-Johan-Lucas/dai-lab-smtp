package prankmail.group;

import prankmail.email.Message;

import java.util.*;

/**
 * Group class allows to create a group of email addresses and a forged email message with a chosen prank message
 */
public class Group {

    /**
     * List of email addresses in the group, the first one is the sender, the others are the recipients
     */
    private List<String> emailAddresses;

    /**
     * String containing the prank message which will be the content of the forged email
     */
    private Message prankMessage;

    /**
     * List of forged email messages for each recipient
     */
    private String forgedMessage;

    /**
     * Constructor
     * @param emailAddresses List of email addresses in the group
     * @param prankMessage prank message to send
     */
    public Group(List<String> emailAddresses, Message prankMessage) {
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
    public Message getPrankMessage() {
        return prankMessage;
    }

    /**
     * Get the forged email messages for the group
     * @return Forged email messages
     */
    public String getForgedMessage() {
        return forgedMessage;
    }

    /**
     * Add a forged email message to the group
     * @param forgedMessage Forged email message
     */
    public void addForgedMessages(String forgedMessage) {
        this.forgedMessage = forgedMessage;
    }
}
