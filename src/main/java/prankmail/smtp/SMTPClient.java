package prankmail.smtp;

import prankmail.group.Group;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * SMTPClient class allows to send forged email messages to the SMTP server
 */
public class SMTPClient {
    private String serverAddress;
    private int serverPort;
    private final Group group;

    /**
     * Constructor
     * @param group The group prank
     */
    public SMTPClient(Group group) {
        this.group = group;
    }

    /**
     * Constructor
     * @param group The group prank
     * @param address The address of the SMTP server
     * @param port The port of the SMTP server
     */
    public SMTPClient(Group group, String address, int port) {
        this(group);
        this.serverAddress = address;
        this.serverPort = port;
    }

    /**
     * Connect to the SMTP server and send forged email messages
     */
    public void run() {
        // Connect to the SMTP server
        try (Socket socket = new Socket(serverAddress, serverPort);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {

            // Send the forged email messages to the SMTP server
            send(in, out);

        } catch (Exception e) {
            throw new RuntimeException("Error: " + e);
        }
    }

    /**
     * Send forged email messages to the SMTP server
     * @param in The input stream
     * @param out The output stream
     * @throws IOException If there is an error reading or writing the streams
     */
    private void send(BufferedReader in, BufferedWriter out) throws IOException {
        // Introduce ourselves to the SMTP server
        String line;
        if (!(line = in.readLine()).startsWith("220")) throw new RuntimeException(line);

        out.write("EHLO mailDev.com\n");
        out.flush();
        do {
            line = in.readLine();
            if (!line.startsWith("250")) {
                throw new RuntimeException(line);
            }
        } while (!line.startsWith("250 "));

        // Get the list of email addresses and forged email messages
        var emails = new ArrayList<>(group.getEmailAddresses());
        var forgedMessage = group.getForgedMessage();

        // Send a forged email for each victim
        out.write("MAIL FROM:<" + emails.getFirst() + ">\r\n");
        out.flush();
        if (!(line = in.readLine()).startsWith("250")) throw new RuntimeException(line);

        for (int i = 1; i < emails.size(); ++i) {
            out.write("RCPT TO:<" + emails.get(i) + ">\r\n");
            out.flush();
            if (!(line = in.readLine()).startsWith("250"))
                throw new RuntimeException(line);
        }
        out.write("DATA\r\n");
        out.flush();
        if (!(line = in.readLine()).startsWith("354")) throw new RuntimeException(line);

        out.write(forgedMessage);
        out.flush();
        if (!(line = in.readLine()).startsWith("250")) throw new RuntimeException(line);

        // Quitting after sending all the forged emails of the group
        out.write("QUIT\n");
        out.flush();
        if (!(line = in.readLine()).startsWith("221")) throw new RuntimeException(line);
    }
}
