package tn.esprit.piblogfashion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;

import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import tn.esprit.piblogfashion.entities.Event;
import tn.esprit.piblogfashion.services.EventServices;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class EventListController implements Initializable {

    @FXML
    private TableView<Event> table;
    @FXML
    private TableColumn<Event, String> event_title;

    @FXML
    private TableColumn<Event, Date> event_date;

    EventServices es = new EventServices();
    ObservableList<Event> data = FXCollections.observableArrayList(es.FindEvent());
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
    private Button btn_pic;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_clear;

    String imageFile1;
    @FXML
    private TextField recherche;

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
                    File file = new File("C:\\wamp64\\www\\ImagesHulk\\" + e.getEvent_pic());
                    Image image1 = new Image(file.toURI().toString());
                    iv_pic.setImage(image1);
                    btn_delete.setDisable(false);
                    btn_update.setDisable(false);
                    btn_add.setDisable(true);
                    btn_pic.setDisable(true);
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

    @FXML
    private void event_add(ActionEvent event) {
        Event e = new Event(Date.valueOf(dp_date.getValue()), tf_address.getText(), tf_title.getText(), tf_desc.getText(), imageFile1);
        es.AddEvent(e);
        data.removeAll(data);
        for (Event ev : FXCollections.observableArrayList(es.FindEvent())) {
            data.add(ev);

        }
    }

    @FXML
    private void event_update(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            es.UpdateEvent(new Event(Date.valueOf(dp_date.getValue()), tf_address.getText(), tf_title.getText(), tf_desc.getText(), imageFile1), table.getSelectionModel().getSelectedItem().getEvent_id());
            data.removeAll(data);
            for (Event e : FXCollections.observableArrayList(es.FindEvent())) {
                data.add(e);
            }
            clear();
        }
    }

    @FXML
    private void event_delete(ActionEvent event) {
        ButtonType okButtonType = new ButtonType("Ok", ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonData.OK_DONE) {
            if (table.getSelectionModel().getSelectedItem() != null) {
                es.DeleteEvent(table.getSelectionModel().getSelectedItem().getEvent_id());
                data.removeAll(data);
                for (Event e : FXCollections.observableArrayList(es.FindEvent())) {
                    data.add(e);
                }

            }
            clear();
        } else {
            System.out.println("Cancel");
        }

    }

    @FXML
    private void clear(ActionEvent event) {
        clear();
    }

    private void clear() {
        table.getSelectionModel().clearSelection();
        tf_address.clear();
        tf_desc.clear();
        tf_title.clear();
        dp_date.setValue(null);
        btn_delete.setDisable(true);
        btn_update.setDisable(true);
        btn_add.setDisable(false);
        iv_pic.setImage(null);
        btn_pic.setDisable(false);

    }

    public void CopyImage(String url, String imageDestination) throws IOException {

        //URL l'emplacement de fichier image sous wamp exemple (http://localhost/image/product)
        InputStream inputStream = new FileInputStream(imageDestination);//upload l'image
        System.out.println("Start uploading second file");

        OutputStream output = new FileOutputStream(url);
        byte[] bytesIn = new byte[4096];
        int read = 1;
        while ((read = inputStream.read(bytesIn)) != -1) {//copier l'image au serveur
            output.write(bytesIn, 0, read);
        }
        output.close();
        inputStream.close();
    }

    @FXML
    private void upload(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {

            Image image = new Image(selectedFile.toURI().toString());
            iv_pic.setImage(image);
            imageFile1 = selectedFile.getName();
            int pos = imageFile1.lastIndexOf("/");
            if (pos > 0) {
                imageFile1 = imageFile1.substring(0, pos);
            }
            iv_pic.setImage(image);
            String emplacement = "C:\\wamp64\\www\\ImagesHulk\\" + imageFile1;
            System.out.println(emplacement);
            CopyImage(emplacement, selectedFile.toPath().toString());

        } else {
            System.out.println("file doesn't exist");
        }
    }

}
