package org.artorg.tools.phantomData.server.boot;

import static org.toilelibre.libe.curl.Curl.curl;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.artorg.tools.phantomData.server.BootApplication;
import org.springframework.boot.SpringApplication;

public class BootUtilsServer {
	public static boolean isRunnableJarExecution(Class<?> sampleClassInApplication) {
		String uriPath = null;
		try {
			uriPath = sampleClassInApplication.getProtectionDomain().getCodeSource().getLocation().toURI().toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		if (uriPath.contains(".jar")) {
			Pattern pattern = Pattern.compile("jar:file:/(.*)[\\u002e]jar");
			Matcher m = pattern.matcher(uriPath);
			return m.find();
		}
		return false;
	}
	
	public static File getRunnableJarExecutionDirectory(Class<?> sampleClassInApplication) {
		String uriPath = null;
		
		try {
			uriPath = sampleClassInApplication.getProtectionDomain().getCodeSource().getLocation().toURI().toString();
		} catch (URISyntaxException e2) {
			e2.printStackTrace();
		}
		
		final Pattern pattern = Pattern.compile("jar:file:/(.*)[\\u002e]jar");
		Matcher m = pattern.matcher(uriPath);
		if(m.find())
			return new File(m.group(1)).getParentFile();
		
		throw new IllegalArgumentException();

	}
	
	public static String toString(Properties properties) {
		return properties.entrySet().stream()
				.map(e -> e.getKey().toString() +" = " +e.getValue().toString())
				.collect(Collectors.joining("\n"));
	}

	public static Connection getConnection(LaunchConfigurationServer config) {
		try {
			Class.forName (config.getApplicationProperties().getProperty("sql.driver"));
			return DriverManager.getConnection (
					config.getSpringDatasourceUrl(), 
					config.getDatabaseUsername(),
					config.getDatabasePassword()); 
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		throw new RuntimeException();
	}
	
	public static boolean isConnected(LaunchConfigurationServer config) {
		return isConnected(config.getUrlLocalhost());
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
	
	public static void deleteDatabase(LaunchConfigurationServer launchConfig) {
		deleteDatabase(launchConfig.getDatabasePath(),
				launchConfig.getDatabaseFilename(),
				launchConfig.getDatabaseFileExtension());
	}
	
	public static void deleteDatabase(String databasePath, String databaseFilename, String databaseFileExtension) {
		File database = new File(databasePath
				+"/" +databaseFilename
				+"." +databaseFileExtension);
		if (database.exists())
			database.delete();
		File databaseMV = new File(databasePath
				+"/" +databaseFilename +".mv"
				+"." +databaseFileExtension);
		if (databaseMV.exists())
			databaseMV.delete();
		try {
			FileUtils.deleteDirectory(new File(databasePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteFileStructure(LaunchConfigurationServer launchConfig) {
		File home = new File(launchConfig.getParentDirectory());
		File database = new File(launchConfig.getDatabasePath());
		File filebase = new File(launchConfig.getFilesPath());
		File log = new File(launchConfig.getLogsPath());
		
		try {
			FileUtils.deleteDirectory(database);
			FileUtils.deleteDirectory(filebase);
			FileUtils.deleteDirectory(log);
//			FileUtils.deleteDirectory(home);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void prepareFileStructure(LaunchConfigurationServer config) {
		List<String> paths = new ArrayList<String>();
		
		paths.add(config.getParentDirectory());
		paths.add(config.getDatabasePath());
		paths.add(config.getFilesPath());
		paths.add(config.getLogsPath());
		
		prepareFileStructure(paths);
	}
	
	public static void prepareFileStructure(List<String> paths) {
		paths.forEach(path -> {
			new File(path).mkdirs();
		});
	}
	
	public static void startingServer(LaunchConfigurationServer config, String[] args) {
		startingServer(config.getBootApplicationClass(), config.getUrlLocalhost(), args);
		
		if (!isConnected(config.getUrlLocalhost())) 
			SpringApplication.run(BootApplication.class, args);
	}
	
	public static void startingServer(Class<?> bootApplicationClass, String urlLocalhost, String[] args) {
		if (!isConnected(urlLocalhost)) 
			SpringApplication.run(bootApplicationClass, args);
	}
	
	public static void shutdownServer(LaunchConfigurationServer config) {
		shutdownServer(config.getUrlLocalhost(), config.getUrlShutdownActuator());
	}
	
	public static void shutdownServer(String urlLocalhost, String urlShutdownActuator) {
		if (isConnected(urlLocalhost)) {
			curl("-X POST " +urlShutdownActuator);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
//	public void logInfos() {
//		new File(getAbsoluteLogPath()).mkdir();
//		File connectionSettings = new File(getAbsoluteLogPath() +"\\ConnectionSettings.txt");
//		try {
//			if (connectionSettings.exists())
//				connectionSettings.delete();
//			connectionSettings.createNewFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			PrintWriter out = new PrintWriter(getAbsoluteLogPath() +"\\ConnectionSettings.txt");
//			out.write("Localhost URL:            " +getUrlLocalhost() +"\n");
//			out.write("URL of shutdown actuator: " +getUrlShutdownActuator() +"\n");
//			out.write("Database path:            " +getApplicationProperties().getProperty("spring.datasource.url") +"\n");
//			out.write("Files path:               " +getAbsoluteFileBasePath() +"\n");
//			out.flush();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		try {
//			FileUtils.copyFile(connectionSettings, new File(getAbsoluteLogPath() +"/ConnectionSettings.txt"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}

}
