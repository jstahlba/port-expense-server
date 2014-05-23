package jds.expense.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDB {
	protected static Connection connect() throws SQLException {
		String hostname = DBConfig.getHost();   //System.getProperty("com.mysql.fabric.testsuite.hostname");
		String port = DBConfig.getPort();//System.getProperty("com.mysql.fabric.testsuite.port");
		String database = DBConfig.getDBName();//System.getProperty("com.mysql.fabric.testsuite.database");
		String user = DBConfig.getUser();//System.getProperty("com.mysql.fabric.testsuite.username");
		String password = DBConfig.getPass();//System.getProperty("com.mysql.fabric.testsuite.password");
	
		String baseUrl = "jdbc:mysql://" + hostname + ":" + Integer.valueOf(port) + "/";
	
		// 1. Create database and table for our demo
		Connection rawConnection = DriverManager.getConnection(
				baseUrl + database,
				user,
				password);
		return rawConnection;
	}
}
