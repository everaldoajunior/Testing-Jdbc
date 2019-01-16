package br.com.junioralmeida.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database{
	
	public static Connection getConnection() throws SQLException{
			
		Connection connectionTest = null;
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:postgres://localhost:5432/loja1", "postgres", "admin");
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
			return connectionTest;
			
	}
}
