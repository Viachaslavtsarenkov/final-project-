package by.tsarenkov.shop.service;


import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

    private static FileInputStream emailPropertiesFile;
    private static final Properties properties;
    private static final String email;
    private static final String password;
    private static final Session session;

    static {
        properties = new Properties();
        try {
            emailPropertiesFile = new FileInputStream(
                    Thread.currentThread().getContextClassLoader().getResource("").getPath()
                            + "email.sender.properties");
            System.out.println(emailPropertiesFile.available());
            properties.load(emailPropertiesFile);
        } catch (IOException e) {
        }
        email = properties.getProperty("email");
        password = properties.getProperty("password");
    }

    static {
         session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
    }

    public EmailService() {

    }

    public static void sendRegistrationMessage(String to) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(to));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Регистрация");
            message.setText("message");
            Transport.send(message);
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }


}
