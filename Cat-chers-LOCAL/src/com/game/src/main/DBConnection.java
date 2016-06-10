package com.game.src.main;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection getConnection() throws Exception {
		 Class.forName("com.mysql.jdbc.Driver");

	     String dbUser = "nlcortez";
	     String dbPass = "catslikedata";
	     String host = "csc-db0.csc.calpoly.edu";
	     String dbName = "nlcortez";

	     String dbUrl = String.format("jdbc:mysql://%s/%s?autoReconnect=true", host, dbName);
	     
	     return DriverManager.getConnection(dbUrl, dbUser, dbPass);

	}
	
	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception ex) {
			System.err.println("Unable to close DBConnection.");
			System.exit(1);
		}
	}	
}
