package main.java.ch.heig.dai.lab.prankmail;

import main.java.ch.heig.dai.lab.prankmail.email.EmailMessage;

import java.util.*;

public class Group {
    private List<String> emailAddresses;
    private String message;

    /**
     * Constructor
     * @param emailAddresses List of email addresses in the group
     * @param forgedMessage The forged email message for the group
     */
    public Group(List<String> emailAddresses, EmailMessage forgedMessage) {
        this.emailAddresses = new ArrayList<>(emailAddresses);
        this.message = forgedMessage.forge();
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
    public String getMessage() {
        return message;
    }
}
