package prankmail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SMTPClientTest {
    @Test
    public void testSendMessage(){
        try {
            Controller ctrl = new Controller("src/test/java/prankmail/test_email.txt",
                    "src/test/java/prankmail/test_message.txt");

            ctrl.sendPrankEmails(
                    ctrl.generatePrankGroups(4),
                    "localhost",
                    1025
            );

        }catch (Exception e){
            fail(e);
        }
    }
}
