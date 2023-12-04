package prankmail.email;

import java.util.*;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;

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
        subject =

        this.from = from;
        this.to = to;
        this.subject = message.getSubject();
        this.content = message.getContent();
        this.date = new Date().toString();
    }

    /**
     * Constructor
     * @param from Sender email address
     * @param to Recipient email address
     * @param subject Subject of the email
     * @param content Content of the email
     */
    public EmailMessage(String from, String to, String subject, String content) {
        this(from, to, new Message(subject, content));
    }

    /**
     * Forge the email message with the given parameters
     * @return Email message
     */
    public String forge() {
        return "From: " + getName(from) + " <" + from + ">\r\n" +
                "To: " + to + "\r\n" +
                "Date: " + date + "\r\n" +
                "Subject: " + encodeSubject(subject) + "\r\n" +
                encodeContent(content);
    }

    /**
     * Get the name of the sender from his email address
     * @param email Email address of the sender
     * @return Name of the sender
     */
    public static String getName(String email) {
        String[] names = email.split("@")[0].split("\\.");
        StringBuilder nameBuilder = new StringBuilder();
        for(String name:names){
            nameBuilder.append(Pattern.compile("^.").matcher(name).replaceFirst(m -> m.group().toUpperCase()));
            nameBuilder.append(" ");
        }
        nameBuilder.deleteCharAt(nameBuilder.length()-1);
        return nameBuilder.toString();
    }

    /**
     * Validate email address format
     * @param email Email address to validate
     * @throws IllegalArgumentException If the email address is invalid
     */
    public static boolean validateEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

        return email.matches(regex);
    }

    /**
     * Encode the subject of the email message in UTF-8
     * @param subject Subject of the email message
     * @return Encoded subject
     */
    private static String encodeSubject(String subject) {
        return "=?UTF-8?B?" + Base64.getEncoder().encodeToString(subject.getBytes(UTF_8)) + "?=";
    }

    /**
     * Encode the content of the email message in UTF-8
     * @param content Content of the email message
     * @return Encoded content
     */
    private static String encodeContent(String content) {
        return "Content-Type: text/plain; charset=UTF-8\r\n\r\n" + content + "\r\n\r\n.\r\n";
    }
}
