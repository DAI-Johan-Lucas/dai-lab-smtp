package prankmail;

import prankmail.Controller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SMTPClientTest {
    @Test
    public void testSendMessage(){
        try {
//            Controller ctrl = new Controller("C:\\Users\\Mikami\\Documents\\DAI" +
//                    "\\labo\\dai-lab-smtp\\src\\test\\java\\ch\\heig\\dai\\lab" +
//                    "\\prankmail\\test_email.txt", "C:\\Users\\Mikami\\Documents\\DAI\\labo\\dai-lab-smtp\\src\\test\\java\\ch\\heig\\dai\\lab\\prankmail\\test_message.txt");

            Controller ctrl = new Controller("src/test/java/prankmail/test_email.txt", "src/test/java/prankmail/test_message.t");

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
