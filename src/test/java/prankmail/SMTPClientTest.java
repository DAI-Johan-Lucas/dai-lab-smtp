package prankmail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SMTPClientTest {
    @Test
    public void testSendMessage(){
        try {
            Controller ctrl = new Controller("src/test/java/prankmail/test_email.txt",
                    "src/test/java/prankmail/test_message.json");

            ctrl.sendPrankEmails(
                    ctrl.generatePrankGroups(2),
                    "localhost",
                    1025
            );

        }catch (Exception e){
            fail(e);
        }
    }
}
