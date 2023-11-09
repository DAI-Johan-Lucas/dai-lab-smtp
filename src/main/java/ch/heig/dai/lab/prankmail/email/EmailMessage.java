package main.java.ch.heig.dai.lab.prankmail.email;

import java.util.*;

/**
 * EmailMessage class allows to forge an email message with the given parameters
 */
public class EmailMessage {
    private String from;
    private String to;
    private String date;
    private String subject;
    private String content;

    /**
     * Constructor
     * @param from Sender email address
     * @param to Recipient email address
     * @param subject Subject of the email
     * @param content Data of the email
     */
    public EmailMessage(String from, String to, String subject, String content) {
        this.from = "From: " + getName(from) + " <" + from + ">";
        this.to = "To: <" + to + ">";
        this.subject = "Subject: " + subject;
        this.content = content;
        this.date = "Date: " + new Date().toString();
    }

    /**
     * Constructor
     * @param from Sender email address
     * @param to Recipient email address
     * @param subject Subject of the email
     * @param content Data of the email
     * @param date Date of the email
     */
    public EmailMessage(String from, String to, String subject, String content, String date) {
        this(from, to, subject, content);
        this.date = "Date: " + date;
    }

    /**
     * Get the name of the sender
     * @param email Email address of the sender
     * @return Name of the sender
     */
    private String getName(String email) {
        String name = email.split("@")[0];
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    /**
     * Forge the email message
     * @return Email message
     */
    public String forge() {
        return from + "\n" + to + "\n" + date + "\n" + subject + "\n\n" + content + "\n\n.\n";
    }
}
