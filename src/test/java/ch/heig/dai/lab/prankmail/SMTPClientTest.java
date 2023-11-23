package ch.heig.dai.lab.prankmail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SMTPClientTest {
    @Test
    public void testSendMessage(){
        try {
//            Controller ctrl = new Controller("C:\\Users\\Mikami\\Documents\\DAI" +
//                    "\\labo\\dai-lab-smtp\\src\\test\\java\\ch\\heig\\dai\\lab" +
//                    "\\prankmail\\email_address.txt", "C:\\Users\\Mikami\\Documents\\DAI\\labo\\dai-lab-smtp\\src\\test\\java\\ch\\heig\\dai\\lab\\prankmail\\prank_message.txt");

            Controller ctrl = new Controller();

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
