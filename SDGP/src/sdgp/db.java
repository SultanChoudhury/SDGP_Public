/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdgp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;


/**
 *
 * @author lelouch
 */
public class db {
    private static Connection conn;
    private static Statement st;
    
    
    public static Connection users_db(){
        
        try{
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:Users.db";
            conn = DriverManager.getConnection(url);
//            st = conn.createStatement();
            System.out.println("Connection Successful");
           

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return conn;
        
        
    }
    
    public static Connection EU_data(){
        try{
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:EU_data.db";
            conn = DriverManager.getConnection(url);
//            st = conn.createStatement();
            System.out.println("Connection Successful");
           

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return conn;
    }
    
}
