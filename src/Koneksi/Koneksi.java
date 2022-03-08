/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author harip
 */
public class Koneksi {
/**
     * @param args the command line arguments
     */
      private static Connection MySQLConfig;
    
//    public static void main(String[] args) {
//        
//    }
//     private static Connection MySQLConfig;
    
    public static Connection configDB()throws SQLException{
        if(MySQLConfig == null)
        try{
            String url ="jdbc:mysql://localhost:3306/isi_ulang";
            String user="root";
            String pass ="";
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            MySQLConfig = DriverManager.getConnection(url, user, pass);
        }catch(SQLException e ){
            System.out.println("Koneksi ke Database Gagal " + e.getMessage() );
    }
        return MySQLConfig;
    }
    
}