package br.com.everaldojr.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database{
	
	public static Connection getConnection() throws SQLException{
		 
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/loja1", "postgres", "admin");
		
		return connection;
		
	}
}
