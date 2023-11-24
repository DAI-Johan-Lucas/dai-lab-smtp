package prankmail.email;

public class Message {
    private final String subject;
    private final String content;

    public Message(String subject, String body) {
        this.subject = subject;
        this.content = body;
    }

    public String getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }
}
