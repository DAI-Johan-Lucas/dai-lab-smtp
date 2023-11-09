package ch.heig.dai.lab.prankmail.email;

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
     * @param content Data of the email
     */
    public EmailMessage(String from, String to, String content) {
        validateEmail(from);
        validateEmail(to);

        this.from = from;
        this.to = to;
        this.content = content;
        this.subject = "No subject";
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
        this(from, to, content);
        this.subject = subject;
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
     * Get the sender
     * @return String
     */
    public String getFrom(){
        return from;
    }

    /**
     * Get recipients
     * @return Email list of recipients
     */
    public ArrayList<String> getTo(){
        return new ArrayList<String>(Arrays.asList(to.split(" ")));
    }

    /**
     * Forge the email message
     * @return Email message
     */
    public String forge() {
        return "From: " + getName(from) + " <" + from + ">\r\n" +
                "To: " + " <" + to + ">\r\n" +
                "Date: " + date + "\r\n" +
                "Subject: " + subject + "\r\n" +
                "\r\n" +
                content + "\r\n" +
                "\r\n" +
                ".\r\n";
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
