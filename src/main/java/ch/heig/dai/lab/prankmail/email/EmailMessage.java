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
        validateEmail(from);
        validateEmail(to);

        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.date = new Date().toString();
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
        this.date = date;
    }

    /**
     * Forge the email message
     * @return Email message
     */
    public String forge() {
        StringBuilder message = new StringBuilder();

        message.append("From: ").append(getName(from)).append(" <").append(from).append(">\r\n");
        message.append("To: ").append(" <").append(to).append(">\r\n");
        message.append("Date: ").append(date).append("\r\n");
        message.append("Subject: ").append(subject).append("\r\n");
        message.append("\r\n");
        message.append(content).append("\r\n");
        message.append("\r\n");
        message.append(".\r\n");

        return message.toString();
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
     * Validate email address format
     * @param email Email address to validate
     * @throws IllegalArgumentException if the email address is not valid
     */
    private void validateEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        if (!email.matches(regex)) {
            throw new IllegalArgumentException("Invalid email address: " + email);
        }
    }
}
