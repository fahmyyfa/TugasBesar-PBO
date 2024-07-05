package org.example.demo;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SendEmail {

    private final String username = "adnanamhar138@gmail.com";
    private final String password = "pnsufzykqwjkwwrp";
    private Properties props;
    private Session session;

    public SendEmail() {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void sendEmail(String recipientEmail, String subject, String body) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendWithAttachment(String recipientEmail, String subject, String body, byte[] attachmentData, String fileName) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            // Create message body part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // Create multipart message
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Create attachment part
            MimeBodyPart attachmentPart = new MimeBodyPart();
            ByteArrayDataSource source = new ByteArrayDataSource(attachmentData, "application/pdf");
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(fileName);
            multipart.addBodyPart(attachmentPart);

            // Set the multipart message to the email
            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Email with attachment sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String dateinfo() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(currentDate);
    }
}