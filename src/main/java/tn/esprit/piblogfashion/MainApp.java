package tn.esprit.piblogfashion;

import java.sql.Date;
import java.sql.Statement;
import java.util.Calendar;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.piblogfashion.entities.Event;
import tn.esprit.piblogfashion.services.EventServices;
import utils.DbConnexion;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
        
        EventServices ser=new EventServices();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Event e =new Event(date,"address2","title2","desc2","pic2");
        ser.DeleteEvent(6);
     
        

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
