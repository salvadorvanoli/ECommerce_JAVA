package clases;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class ServicioEmail {
    private Properties propiedadesEmail;

    public ServicioEmail() {
        // Cargar las propiedades desde un archivo
    	propiedadesEmail = new Properties();
        try (InputStream input = new FileInputStream("src/propiedades/mail.properties")) {
        	propiedadesEmail.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendEmail(String toAddress, String subject, String htmlMessage) {
        String username = propiedadesEmail.getProperty("mail.smtp.username");
        String password = propiedadesEmail.getProperty("mail.smtp.password");

        Session session = Session.getInstance(propiedadesEmail, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject(subject);

            // Configurar el contenido HTML
            message.setContent(htmlMessage, "text/html");

            // Enviar el correo
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
