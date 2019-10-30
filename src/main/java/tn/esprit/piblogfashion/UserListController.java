package tn.esprit.piblogfashion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.esprit.piblogfashion.entities.Event;
import tn.esprit.piblogfashion.services.EventServices;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class UserListController implements Initializable {

    @FXML
    private TableView<Event> table;
    @FXML
    private TableColumn<Event, String> event_title;
    @FXML
    private TableColumn<Event, Date> event_date;
    @FXML
    private TextField tf_title;
    @FXML
    private TextField tf_desc;
    @FXML
    private TextField tf_address;
    @FXML
    private DatePicker dp_date;
    @FXML
    private ImageView iv_pic;
    @FXML
    private TextField recherche;
    EventServices es = new EventServices();
ObservableList<Event> data = FXCollections.observableArrayList(es.FindEvent());
    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        event_title.setCellValueFactory(new PropertyValueFactory("event_title"));
        event_date.setCellValueFactory(new PropertyValueFactory("event_date"));
        
        table.setItems(data);

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    Event e = table.getSelectionModel().getSelectedItem();
                    System.out.println();
                    tf_title.setText(e.getEvent_title());
                    tf_desc.setText(e.getEvent_desc());
                    tf_address.setText(e.getEvent_address());
                    dp_date.setValue(e.getEvent_date().toLocalDate());
                    File file = new File("C:\\wamp64\\www\\ImagesHulk\\"+e.getEvent_pic());
                    Image image1 = new Image(file.toURI().toString());
                    iv_pic.setImage(image1);
                    
                }
            }
        });
        
        recherche.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filtrerEventList((String) oldValue, (String) newValue);
            }

            

        });
     }
void filtrerEventList(String oldValue, String newValue) {
        EventServices evs = new EventServices();
            ObservableList<Event> filteredList = FXCollections.observableArrayList();
            if (recherche.getText() == null || newValue == null) {
                table.setItems((ObservableList<Event>) evs.FindEvent());
            } else {
                table.setItems((ObservableList<Event>) evs.FindEvent());
                newValue = newValue.toUpperCase();

                for (Event e : table.getItems()) {

                    String filterEventName = e.getEvent_title();

                    if (filterEventName.toUpperCase().contains(newValue)) {
                        filteredList.add(e);
                    }
                }
                table.setItems(filteredList);
            }
}


}