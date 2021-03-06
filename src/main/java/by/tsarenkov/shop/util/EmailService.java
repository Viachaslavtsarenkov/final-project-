package by.tsarenkov.shop.util;


import by.tsarenkov.shop.dao.impl.SQLOrderDAO;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

    private static Logger LOGGER = Logger.getLogger(EmailService.class);

    private static FileInputStream emailPropertiesFile;
    private static final Properties properties;
    private static final String email;
    private static final String password;
    private static final Session session;
    private static final String contentMessage = "";
    private static final String subjectMessage = "";
    private static final String activationLink = "http://127.0.1.1:8080/shop_war_exploded/controller?" +
            "command=activation&" +
            "login=lg&" +
            "code=cd";

    static {
        properties = new Properties();
        try {
            emailPropertiesFile = new FileInputStream(
                    Thread.currentThread().getContextClassLoader().getResource("").getPath()
                            + "email.sender.properties");

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

    public static void sendRegistrationMessage(String to, String code) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(to));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subjectMessage);
            message.setText(activationLink.replace("lg", to).replace("cd",code));
            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.error("Exception was thrown: " + e);
        }
    }


}
