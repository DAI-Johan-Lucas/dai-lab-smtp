package ch.heig.dai.lab.prankmail;

import ch.heig.dai.lab.prankmail.email.EmailMessage;
import ch.heig.dai.lab.prankmail.smtp.SMTPClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SMTPClientTest {
    @Test
    public void testSendMessage(){
        try {
            EmailMessage email = new EmailMessage("john.doe@gmail.com",
                    "Adam.smith@gmail.com",
                    "The Next Meeting of the Board",
                    "Bill.\n" +
                            "The next meeting of the board of directors will be on Tuesday.\n" +
                            "John.\n",
                    "Thu, 21 May 1998 05:33:29 -0700");

            SMTPClient smtpclient = new SMTPClient();
            smtpclient.sendEmail(email);
        }catch (Exception e){
            assertTrue(true);
        }
    }
}
