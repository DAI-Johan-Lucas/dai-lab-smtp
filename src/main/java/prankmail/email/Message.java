package prankmail.email;

/**
 * Message class allows to store the subject and the content of an email message
 */
public class Message {
    private final String subject;
    private final String content;

    /**
     * Constructor
     * @param subject Subject of the email message
     * @param content Content of the email message
     */
    public Message(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    /**
     * Get the content of the email message
     * @return Content of the email message
     */
    public String getContent() {
        return content;
    }

    /**
     * Get the subject of the email message
     * @return Subject of the email message
     */
    public String getSubject() {
        return subject;
    }
}
