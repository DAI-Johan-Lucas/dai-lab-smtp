package prankmail;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 5) {
            System.out.println("\u001B[94mUsage: java -jar prankmail.jar <emailFile> <messageFile> " +
                    "<numberOfGroups> <serverAddress> <serverPort>\u001B[0m");
            return;
        }


        Controller ctrl = new Controller(args[0], args[1]);

        ctrl.sendPrankEmails(
                ctrl.generatePrankGroups(Integer.parseInt(args[2])),
                args[3],
                Integer.parseInt(args[4])
        );
    }
}