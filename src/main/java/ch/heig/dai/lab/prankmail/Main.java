package ch.heig.dai.lab.prankmail;

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Usage: Main <emailFile> <messageFile> <numberOfGroups>");
            return;
        }

        Controller ctrl = new Controller(args[1], args[2]);

        ctrl.sendPrankEmails(
                ctrl.generatePrankGroups(Integer.parseInt(args[3])),
                "127.0.0.1",
                25
        );
    }
}