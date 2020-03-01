package com.imeage.CrawTest.Utils.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection(String databaseName) throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(String.format("jdbc:mysql://172.17.0.14:3306/%s?characterEncoding=utf-8"
				, databaseName),"root","zhuming52hz");
		return conn;
	}
	public static void close(Connection conn) {
		if ( conn!= null) {
			try {
				conn.close();
			} catch (SQLException e) {
				
			}
		}
	}
}
