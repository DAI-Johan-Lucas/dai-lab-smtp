package ch.heig.dai.lab.prankmail;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 6) {
            System.out.println("Usage: Main <emailFile> <messageFile> <numberOfGroups> <serverAddress> <serverPort>");
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