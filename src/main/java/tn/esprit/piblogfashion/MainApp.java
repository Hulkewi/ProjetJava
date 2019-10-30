package tn.esprit.piblogfashion;

import com.sun.mail.smtp.SMTPTransport;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tn.esprit.piblogfashion.entities.Event;
import tn.esprit.piblogfashion.services.EventServices;
import utils.DbConnexion;

public class MainApp extends Application {
    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final String USERNAME = "khalilbelamine2015@gmail.com";
    private static final String PASSWORD = "Hulk50480627";

    private static final String EMAIL_FROM = "khalilbelamine2015@gmail.com";
    private static final String EMAIL_TO = "souab1993@gmail.com";
    private static final String EMAIL_TO_CC = "";

    private static final String EMAIL_SUBJECT = "Test Send Email via SMTP";
    private static final String EMAIL_TEXT = "Hello Java Mail \n ABC123";
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/EventList.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("/fxml/UserList.fxml"));
        Scene scene = new Scene(root);
        Scene scene2 = new Scene(root2);
        Stage stage2 = new Stage();
        scene.getStylesheets().add("/styles/eventlist.css");
        scene2.getStylesheets().add("/styles/userlist.css");
        stage.setTitle("Event List");
        stage2.setTitle("User List");
        stage.setScene(scene);
        stage2.setScene(scene2);

        stage.show();
        stage2.show();

String  d_email = USERNAME,
            d_uname = USERNAME,
            d_password = PASSWORD,
            d_host = "smtp.gmail.com",
            d_port  = "465",
            m_to = "souab1993@gmail.com",
            m_subject = "Indoors Readable File: " ,
            m_text = "This message is from hulk";
    Properties props = new Properties();
    props.put("mail.smtp.user", d_email);
    props.put("mail.smtp.host", d_host);
    props.put("mail.smtp.port", d_port);
    props.put("mail.smtp.starttls.enable","true");
    props.put("mail.smtp.debug", "true");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.socketFactory.port", d_port);
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.socketFactory.fallback", "false");

        Authenticator auth = new Authenticator() {} ;
    Session session = Session.getInstance(props, auth);
    session.setDebug(true);

    MimeMessage msg = new MimeMessage(session);
    try {
        msg.setSubject(m_subject);
        msg.setFrom(new InternetAddress(d_email));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
        msg.setText(m_text);

Transport transport = session.getTransport("smtps");
            transport.connect(d_host, Integer.valueOf(d_port), d_uname, d_password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }



/**
 * The main() method is ignored in correctly deployed JavaFX application. main()
 * serves only as fallback in case the application can not be launched through
 * deployment artifacts, e.g., in IDEs with limited FX support. NetBeans ignores
 * main().
 *
 * @param args the command line arguments
 */
public static void main(String[] args) {
        launch(args);
    }

}
