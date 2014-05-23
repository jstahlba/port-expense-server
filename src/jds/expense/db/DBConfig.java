package jds.expense.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConfig {
	private final static Logger logger = Logger.getLogger(DBConfig.class.getName());

	private static DBConfig instance;
	public static DBConfig getInstance() {
		if(instance == null)
			try {
				instance = new DBConfig();
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Unable to read db properties", e);
				e.printStackTrace();
			}
		return instance;
	}
	
	public static String getHost() {
		return getInstance().host();
	}
	public static String getPort() {
		return getInstance().port();
	}
	public static String getDBName() {
		return getInstance().database();
	}
	public static String getUser() {
		return getInstance().user();
	}
	public static String getPass() {
		return getInstance().pass();
	}

	private Properties properties;
	
	private DBConfig() throws IOException {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("db.properties");
		properties = new Properties();
		properties.load(in);
		in.close();
	}
	
	private String host() {
		return properties.getProperty("db.host");
	}
	private String port() {
		return properties.getProperty("db.port");
	}
	private String database() {
		return properties.getProperty("db.name");
	}
	private String user() {
		return properties.getProperty("db.user");
	}
	private String pass() {
		return properties.getProperty("db.pass");
	}
}
