package services.notificador.enviadores;
import services.notificador.Notificacion;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class EnviadorMail {

        public static void enviar(Notificacion notificacion) {
            final String userName = "tpdds2023g10@gmail.com"; //same fromMail
            final String password = "ybddzazsnnaqzkyi";
            final String toEmail = notificacion.getUsuario().getMail();

            System.out.println("TLSEmail Start");
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
            //props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            // props.put("mail.smtp.ssl.enable", "true");
            // props.put("mail.smtp.socketFactory.port", "587"); //TLS Port

            //Session session = Session.getDefaultInstance(props);
            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication("tpdds2023g10@gmail.com", "ybddzazsnnaqzkyi");

                }
            });

            try{
                MimeMessage message = new MimeMessage(session);
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(notificacion.getUsuario().getMail(), true));
                message.setSubject("Prueba");
                message.setText(notificacion.getMensaje());
                System.out.println("sending...");
                Transport.send(message);
                System.out.println("Sent message successfully....");

            }catch (MessagingException me){
                System.out.println("Exception: "+me);

            }
        }
    }
