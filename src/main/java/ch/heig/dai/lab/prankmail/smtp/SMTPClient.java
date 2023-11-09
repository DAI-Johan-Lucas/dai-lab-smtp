package ch.heig.dai.lab.prankmail.smtp;

import javax.swing.text.html.ImageView;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

public class SMTPClient {
    private final String SMTPSERVER_ADDRESS = "localhost";
    private final int SMTPSERVER_PORT = 1025;

    public void sendEmail(ArrayList<String> listMail, ArrayList<String> mail){
        try (Socket socket = new Socket(SMTPSERVER_ADDRESS, SMTPSERVER_PORT);
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

            out.write("MAIL FROM:<" + listMail.get(0) + ">\n");
            out.flush();
            if(!(line = in.readLine()).startsWith("250"))throw new RuntimeException(line);

            StringBuilder str = new StringBuilder("RCPT TO:");
            Iterator<String> iterator = listMail.iterator();
            iterator.next();
            while (iterator.hasNext()) {
                str.append("<").append(iterator.next()).append(">");
            }
            out.write(str.toString()+"\n");
            out.flush();
            if(!(line = in.readLine()).startsWith("250"))throw new RuntimeException(line);

            out.write("DATA\n");
            out.flush();
            if(!(line = in.readLine()).startsWith("354"))throw new RuntimeException(line);

            str = new StringBuilder();
            iterator = mail.iterator();
            while (iterator.hasNext()){
                str.append(iterator.next()+"\n");
            }
            out.write(str.toString());
            out.flush();
            out.write("\r\n.\r\n");
            out.flush();
            if(!(line = in.readLine()).startsWith("250"))throw new RuntimeException(line);

            out.write("QUIT\n");
            out.flush();
            if(!(line = in.readLine()).startsWith("221"))throw new RuntimeException(line);

        } catch (Exception e) {
            throw new RuntimeException("Error: "+ e.getMessage());
        }
    }

    public static void main(String[] args) {
        ArrayList<String> mail = new ArrayList<>();
        mail.add("Date: Thu, 21 May 1998 05:33:29 -0700");
        mail.add("From: John Q. Public <JQP@bar.com>");
        mail.add("Subject: The Next Meeting of the Board");
        mail.add("To: Jones@xyz.com");
        mail.add("Bill.");
        mail.add("The next meeting of the board of directors will be on Tuesday.");
        mail.add("John.");

        ArrayList<String> addressMail = new ArrayList<>();
        addressMail.add("john.doe@gmail.com");
        addressMail.add("Adam.smith@gmail.com");

        SMTPClient smtpclient = new SMTPClient();
        smtpclient.sendEmail(addressMail, mail);
    }
}
