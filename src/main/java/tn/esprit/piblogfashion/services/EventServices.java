/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.piblogfashion.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.piblogfashion.entities.Event;
import utils.DbConnexion;

/**
 *
 * @author DELL
 */
public class EventServices {

    public void AddEvent(Event e) {
        try {
            String requete2 = "INSERT INTO event (event_date,event_address,event_desc,event_title,event_pic) VALUES (?,?,?,?,?)";
            PreparedStatement pst = DbConnexion.getInstance().cnx.prepareStatement(requete2);
            pst.setDate(1, e.getEvent_date());
            pst.setString(2, e.getEvent_address());
            pst.setString(3, e.getEvent_desc());
            pst.setString(4, e.getEvent_title());
            pst.setString(5, e.getEvent_pic());
            pst.executeUpdate();
            System.out.println("Event added");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void UpdateEvent(Event e, int id) {
        try {
            String requete3 = "UPDATE event SET event_date=?,event_address=?,event_desc=?,event_title=?,event_pic=? WHERE event_id=?";
            PreparedStatement pst2 = DbConnexion.getInstance().cnx.prepareStatement(requete3);
            pst2.setInt(6, id);
            pst2.setDate(1, e.getEvent_date());
            pst2.setString(2, e.getEvent_address());
            pst2.setString(3, e.getEvent_desc());
            pst2.setString(4, e.getEvent_title());
            pst2.setString(5, e.getEvent_pic());
            pst2.executeUpdate();
            System.out.println("Event updated");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void DeleteEvent(int id) {
        try {
            String requete7 = "DELETE FROM event WHERE event_id=?";
            PreparedStatement pst7 = DbConnexion.getInstance().cnx.prepareStatement(requete7);
            pst7.setInt(1, id);
            pst7.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Event> FindEvent() {

        ObservableList<Event> list = FXCollections.observableArrayList();
        try {
            String requete4 = "SELECT * FROM event";
            Statement st5 = DbConnexion.getInstance().cnx.createStatement();
            ResultSet rs = st5.executeQuery(requete4);
            while (rs.next()) {
                Event e = new Event();
                e.setEvent_id(rs.getInt("event_id"));
                e.setEvent_date(rs.getDate("event_date"));
                e.setEvent_address(rs.getString("event_address"));
                e.setEvent_desc(rs.getString("event_desc"));
                e.setEvent_title(rs.getString("event_title"));
                e.setEvent_pic(rs.getString("event_pic"));
                list.add(e);
            }

        } catch (SQLException ex) {
            System.out.println("error");
        }
        return list;

    }
}
