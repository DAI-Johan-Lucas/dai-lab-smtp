package main.java.ch.heig.dai.lab.prankmail;

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Usage: Main <emailFile> <messageFile> <numberOfGroups>");
            return;
        }

        /// TODO je sais pas si le premier arguments = args[0] ou args[1]
        Controller ctrl = new Controller(args[0], args[1]);

        ctrl.sendPrankEmails(
                ctrl.generatePrankGroups(Integer.parseInt(args[2])),
                "127.0.0.1",
                25
        );
    }
}