package prankmail;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 5) {
            System.out.println("Usage: java -jar <emailFile> <messageFile> <numberOfGroups> <serverAddress> <serverPort>");
            return;
        }

        Controller ctrl = new Controller(args[1], args[2]);

        ctrl.sendPrankEmails(
                ctrl.generatePrankGroups(Integer.parseInt(args[3])),
                args[4],
                Integer.parseInt(args[5])
        );
    }
}