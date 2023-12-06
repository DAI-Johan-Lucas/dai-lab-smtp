package prankmail;

import java.io.*;
import static prankmail.manual.ManPage.*;

/**
 * Main class of the prankmail application.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1 && args.length != 6) {
            printInvalidArgumentsMessage();
            return;
        }

        switch (args[0]) {
            case "--help" -> {
                printHelpDocument();
                return;
            }
            case "--email" -> {
                printEmailFileExample();
                return;
            }
            case "--json" -> {
                printJsonFileExample();
                return;
            }
        }

        Controller ctrl = new Controller(args[0], args[1]);

        ctrl.sendPrankEmails(
                ctrl.generatePrankGroups(Integer.parseInt(args[2])),
                args[3],
                Integer.parseInt(args[4]),
                args[5]
        );
    }


}