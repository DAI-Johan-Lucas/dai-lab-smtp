package main.java.ch.heig.dai.lab.prankmail.email;

import java.util.*;

public class EmailMessage {
    private String from;
    private String to;
    private String date;
    private String subject;
    private String content;

    public EmailMessage(String from, String to, String subject, String content) {
        this.from = "From: " + getName(from) + " <" + from + ">";
        this.to = "To: <" + to + ">";
        this.subject = "Subject: " + subject;
        this.content = content;
        this.date = "Date: " + new Date().toString();
    }

    public EmailMessage(String from, String to, String subject, String content, String date) {
        this(from, to, subject, content);
        this.date = "Date: " + date;
    }

    private String getName(String email) {
        String name = email.split("@")[0];
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public String forge() {
        return from + "\n" + to + "\n" + date + "\n" + subject + "\n\n" + content + "\n\n.\n";
    }
}
