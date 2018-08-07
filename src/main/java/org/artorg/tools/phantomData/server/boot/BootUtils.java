package org.artorg.tools.phantomData.server.boot;

import static org.toilelibre.libe.curl.Curl.curl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.artorg.tools.phantomData.server.BootApplication;
import org.springframework.boot.SpringApplication;

public class BootUtils {
	public static final String URL_LOCALHOST;
	public static final String URL_SHUTDOWN_ACTUATOR;
	public static final String ABSOLUTE_HOME_PATH;
	public static final String ABSOLUTE_DATABASE_PATH;
	public static final String ABSOLUTE_FILE_BASE_PATH;
	public static final String ABSOLUTE_LOG_PATH;
	
	public static final Properties APPLICATION_PROPERTIES;
	public static final Properties CONFIG_PROPERTIES;

	static {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		APPLICATION_PROPERTIES = new Properties();
		CONFIG_PROPERTIES = new Properties();
		InputStream configPropsInStream = loader.getResourceAsStream("config.properties");
		InputStream appPropsInStream = loader.getResourceAsStream("application.properties");
	    try {
	    	CONFIG_PROPERTIES.load(configPropsInStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    try {
	    	APPLICATION_PROPERTIES.load(appPropsInStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		URL_LOCALHOST = "http://localhost:" +APPLICATION_PROPERTIES.getProperty("server.port");
		URL_SHUTDOWN_ACTUATOR = URL_LOCALHOST +"/actuator/shutdown";
		ABSOLUTE_DATABASE_PATH = CONFIG_PROPERTIES.getProperty("database.path");
		ABSOLUTE_FILE_BASE_PATH = CONFIG_PROPERTIES.getProperty("files.path");
		ABSOLUTE_HOME_PATH = CONFIG_PROPERTIES.getProperty("home.path");
		ABSOLUTE_LOG_PATH = CONFIG_PROPERTIES.getProperty("log.path");
	}

	public static Connection getConnection() {
		try {
			Class.forName (APPLICATION_PROPERTIES.getProperty("sql.driver"));
			return DriverManager.getConnection (
					APPLICATION_PROPERTIES.getProperty("spring.datasource.url"), 
					APPLICATION_PROPERTIES.getProperty("spring.datasource.username"),
					APPLICATION_PROPERTIES.getProperty("spring.datasource.password")); 
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		throw new RuntimeException();
	}
	
	public static boolean isConnected() {
		return isConnected(URL_LOCALHOST);
	}
	
	public static boolean isConnected(String url) {
        try {
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
 
            int code = connection.getResponseCode();
            if (code == 200) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
	
	public static void deleteDatabase() {
		File database = new File(ABSOLUTE_DATABASE_PATH
				+"/" +CONFIG_PROPERTIES.getProperty("database.name.file")
				+"." +CONFIG_PROPERTIES.getProperty("database.name.ext"));
		if (database.exists())
			database.delete();
		File databaseMV = new File(ABSOLUTE_DATABASE_PATH
				+"/" +CONFIG_PROPERTIES.getProperty("database.name.file") +".mv"
				+"." +CONFIG_PROPERTIES.getProperty("database.name.ext"));
		if (databaseMV.exists())
			databaseMV.delete();
		try {
			FileUtils.deleteDirectory(new File(ABSOLUTE_DATABASE_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteFileStructure() {
		File home = new File(ABSOLUTE_HOME_PATH);
		File database = new File(ABSOLUTE_DATABASE_PATH);
		File filebase = new File(ABSOLUTE_FILE_BASE_PATH);
		File log = new File(ABSOLUTE_LOG_PATH);
		
		try {
			FileUtils.deleteDirectory(database);
			FileUtils.deleteDirectory(filebase);
			FileUtils.deleteDirectory(log);
//			FileUtils.deleteDirectory(home);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void prepareFileStructure() {
		File home = new File(ABSOLUTE_HOME_PATH);
		File database = new File(ABSOLUTE_DATABASE_PATH);
		File filebase = new File(ABSOLUTE_FILE_BASE_PATH);
		File log = new File(ABSOLUTE_LOG_PATH);
		
		home.mkdirs();
		database.mkdirs();
		filebase.mkdirs();
		log.mkdirs();
	}
	
	public static void logInfos() {
		new File("log").mkdir();
		File connectionSettings = new File("log/ConnectionSettings.txt");
		try {
			if (connectionSettings.exists())
				connectionSettings.delete();
			connectionSettings.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			PrintWriter out = new PrintWriter("log/ConnectionSettings.txt");
			out.write("Localhost URL:            " +URL_LOCALHOST +"\n");
			out.write("URL of shutdown actuator: " +URL_SHUTDOWN_ACTUATOR +"\n");
			out.write("Database path:            " +APPLICATION_PROPERTIES.getProperty("spring.datasource.url") +"\n");
			out.write("Files path:               " +CONFIG_PROPERTIES.getProperty("files.path") +"\n");
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			FileUtils.copyFile(connectionSettings, new File(ABSOLUTE_LOG_PATH +"/ConnectionSettings.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void startingServer(String[] args) {
		if (!isConnected(URL_LOCALHOST)) 
			SpringApplication.run(BootApplication.class, args);
	}
	
	
	
	public static void shutdownServer() {
		if (isConnected(URL_LOCALHOST)) {
			curl("-X POST " +URL_SHUTDOWN_ACTUATOR);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
