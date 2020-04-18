/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dan-pelusci
 */

//"jdbc:postgresql://192.168.15.106:5432/testes2","postgres","1234"
public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {  
	     try {  
	             Class.forName("org.postgresql.Driver");  
	             return DriverManager.getConnection("jdbc:postgresql://192.168.15.106:5432/testes2","postgres","1234");  
	     }      
	     catch (ClassNotFoundException e) {  
	             throw new SQLException(e.getMessage());  
	     }  
	}
    
}
