package ch.heig.dai.lab.prankmail.smtp;

import ch.heig.dai.lab.prankmail.email.EmailMessage;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

public class SMTPClient {
    private String smtpServer_address;
    private int smtpServer_port;

    public SMTPClient(){
        this.smtpServer_address = "localhost";
        this.smtpServer_port = 1025;

    }
    SMTPClient(String smtpServer_address, int smtpServer_port){
        this.smtpServer_address = smtpServer_address;
        this.smtpServer_port = smtpServer_port;

    }
    public void sendEmail(EmailMessage email){
        try (Socket socket = new Socket(smtpServer_address, smtpServer_port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {
            String line;
            if(!(line = in.readLine()).startsWith("220"))throw new RuntimeException(line);

            out.write("EHLO mailDev.com\n");
            out.flush();
            do {
                line = in.readLine();
                if (!line.startsWith("250")) {
                    throw new RuntimeException(line);
                }
            } while (!line.startsWith("250 "));

            out.write("MAIL FROM:<" + email.getFrom() + ">\r\n");
            out.flush();
            if(!(line = in.readLine()).startsWith("250"))throw new RuntimeException(line);

            ArrayList<String> rcpt = email.getTo();
            for (String s : rcpt) {
                out.write("RCPT TO:<" + s + ">\r\n");
                out.flush();
                if (!(line = in.readLine()).startsWith("250"))
                    throw new RuntimeException(line);
            }

            out.write("DATA\r\n");
            out.flush();
            if(!(line = in.readLine()).startsWith("354"))throw new RuntimeException(line);

            out.write(email.forge());
            out.flush();
            if(!(line = in.readLine()).startsWith("250"))throw new RuntimeException(line);

            out.write("QUIT\n");
            out.flush();
            if(!(line = in.readLine()).startsWith("221"))throw new RuntimeException(line);

        } catch (Exception e) {
            throw new RuntimeException("Error: "+ e.getMessage());
        }
    }
}
