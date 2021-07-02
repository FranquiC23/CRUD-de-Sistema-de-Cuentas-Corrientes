package config;

import java.sql.*;

public class Connect {
	Connection con;
	
	public Connect() {
		try {
			// Connection to Data Base
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BD_CUENTAS_CORRIENTES", "root", "toor");
			//System.out.println("Connected successfully..................");
			
		} catch (Exception e) {
			System.out.println("Error Can't connected");
		}
	}

	public Connection getConnection() {
		return con;
	}

}
