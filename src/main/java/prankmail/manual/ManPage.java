package prankmail.manual;

public class ManPage {

    /**
     * Prints a message to the console to inform the user that the arguments are invalid.
     */
    public static void printInvalidArgumentsMessage() {
        System.out.println("Invalid arguments. Use --help to see the help document.");
    }

    /**
     * Prints a help document to the console.
     */
    public static void printHelpDocument() {
        System.out.println("""

                #### Prankmail help document:

                1. The email file must be a text file containing a list of email addresses, one valid email address per line.
                   --email to see an example of an email file.

                2. The message file must be a JSON format file containing the message to be sent to the victims.
                   --json to see an example of a message JSON file.

                3. The number of groups must be an integer between 1 and 20.

                4. The server address must be a valid IP address or a valid domain name.

                5. The server port must be an integer between 1 and 65535.

                \u001B[94mUsage: java -jar prankmail-1.0-jar-with-dependencies.jar <emailFile> <messageFile> <numberOfGroups> <serverAddress> <serverPort>\u001B[0m
                """);
    }

    /**
     * Prints an example of an email file to the console.
     */
    public static void printEmailFileExample() {
        System.out.println("""
                \u001B[94mExample of email.txt file content:\u001B[0m
                \u001B[33mexample1@domain1.com
                example2@domain2.com
                example3@domain3.com
                example4@domain4.com
                example5@domain5.com\u001B[0m
                """);
    }

    /**
     * Prints an example of a message JSON file to the console.
     */
    public static void printJsonFileExample() {
        System.out.println("""
                    \u001B[94mExample of message.json file content:\u001B[0m
                    \u001B[33m[
                        {
                            "subject":"<yourSubject1>",
                            "content":"<yourContent1>"
                        },
                        {
                            "subject":"<yourSubject2>",
                            "content":"<yourContent2>"
                        },
                        {
                            "subject":"<yourSubject3>",
                            "content":"<yourContent3>"
                        }
                    ]\u001B[0m
                    """);
    }
}
