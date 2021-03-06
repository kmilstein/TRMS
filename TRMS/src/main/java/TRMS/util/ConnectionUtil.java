package TRMS.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private Connection conn;
	
	private static final String URL = System.getenv("TRMS_URL");
	private static final String USERNAME = System.getenv("TRMS_USERNAME");
	private static final String PASSWORD = System.getenv("TRMS_PASSWORD");
	
	public Connection createConnection() throws SQLException {
		
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/trms_db?", "postgres", "password");
		
		return conn;
		
	}

}
