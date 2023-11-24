package prankmail.email;

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
     * @param message Data of the email
     */
    public EmailMessage(String from, String to, Message message) {
        validateEmail(from);
        validateEmail(to);

        this.from = from;
        this.to = to;
        this.content = message.getContent();
        this.subject = message.getSubject();
        this.date = new Date().toString();
    }

    /**
     * Constructor
     * @param from Sender email address
     * @param to Recipient email address
     * @param content Data of the email
     * @param subject Subject of the email
     */
    public EmailMessage(String from, String to, String content, String subject) {
        this(from, to, new Message(subject, content));
    }

    /**
     * Constructor
     * @param from Sender email address
     * @param to Recipient email address
     * @param content Data of the email
     * @param subject Subject of the email
     * @param date Date of the email
     */
    public EmailMessage(String from, String to, String subject, String content, String date) {
        this(from, to, content, subject);
        this.date = date;
    }

    /**
     * Forge the email message with the given parameters
     * @return Email message
     */
    public String forge() {
        return "From: " + getName(from) + " <" + from + ">\r\n" +
                "To: " + " <" + to + ">\r\n" +
                "Date: " + date + "\r\n" +
                "Subject: =?UTF-8?Q?" + subject + "?=\r\n" +
                "\r\n" +
                content + "\r\n" +
                "\r\n" +
                ".\r\n";
    }

    /**
     * Get the name of the sender from his email address
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
     * @throws IllegalArgumentException If the email address is invalid
     */
    public static void validateEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

        if (!email.matches(regex))
            throw new IllegalArgumentException("Invalid email address: " + email);
    }
}
