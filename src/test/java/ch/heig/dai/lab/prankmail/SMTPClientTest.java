package ch.heig.dai.lab.prankmail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SMTPClientTest {
    @Test
    public void testSendMessage(){
        try {
            Controller ctrl = new Controller();

            ctrl.sendPrankEmails(
                    ctrl.generatePrankGroups(6),
                    "localhost",
                    1025
            );

        }catch (Exception e){
            fail(e);
        }
    }
}
