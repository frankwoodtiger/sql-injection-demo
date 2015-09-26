package com.sqlinjection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class OracleConnection {
	public OracleConnection() {
		
	}
	
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return null;
		}
		String url = "jdbc:oracle:thin:@localhost:1521:XE"; 
	      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "SYSTEM");
        props.setProperty("password", "db");
      
        Connection conn = null;

        try {
        	//creating connection to Oracle database using JDBC
        	conn = DriverManager.getConnection(url,props);
        }
	    catch (SQLException e) {
	    	e.printStackTrace();
	    }
        
        return conn;
	}
	
}
