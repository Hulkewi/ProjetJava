package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class DbConnexion {
    private static DbConnexion instance;
    public Connection cnx;
    
    private DbConnexion() {
        try {
            String url="jdbc:mysql://localhost:3306/esprit";
            String login="root";
            String pwd="";
            
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion etablie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    public static DbConnexion getInstance(){
        if(instance == null){
            instance = new DbConnexion();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return cnx;
    }
}
