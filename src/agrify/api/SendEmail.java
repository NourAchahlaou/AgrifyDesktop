/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.api;
    
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


/**
 *
 * @author tbagh
 */
public class SendEmail {

    public void sendEmail(String to, String subject, String body) {
        final String username = "tbagheiyth@gmail.com";
        final String password = "pbrr qsxe wfjz bgwi";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}

    

