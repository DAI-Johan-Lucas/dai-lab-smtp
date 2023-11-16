package ch.heig.dai.lab.prankmail;

import ch.heig.dai.lab.prankmail.email.EmailMessage;
import ch.heig.dai.lab.prankmail.smtp.SMTPClient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SMTPClientTest {
    @Test
    public void testSendMessage(){
        try {
            Controller ctrl = new Controller();

            ctrl.sendPrankEmails(
                    ctrl.generatePrankGroups(2),
                    "localhost",
                    1025
            );

//            EmailMessage email = new EmailMessage("john.doe@gmail.com",
//                    "Adam.smith@gmail.com",
//                    "The Next Meeting of the Board",
//                    "Bill.\n" +
//                            "The next meeting of the board of directors will be on Tuesday.\n" +
//                            "John.\n",
//                    "Thu, 21 May 1998 05:33:29 -0700");
//
//            SMTPClient smtpclient = new SMTPClient();
//            smtpclient.sendEmail(email, 5);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }
}
