package org.artorg.tools.phantomData.server.boot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Properties;
import java.util.function.Consumer;

import org.artorg.tools.phantomData.server.BootApplication;
import org.artorg.tools.phantomData.server.io.IOutil;
import org.artorg.tools.phantomData.server.io.PropertiesFile;
import org.artorg.tools.phantomData.server.model.PhantomFile;

import static org.artorg.tools.phantomData.server.io.IOutil.*;

public class LaunchConfigurationServer {
	private String urlLocalhost;
	private String urlShutdownActuator;
	private String parentDirectory;
	private String databasePath;
	private String homePath;
	

	

	private String filesPath;
	private String logsPath;
	private String databaseFilename;
	private String databaseFileExtension;
	private String springDatasourceUrl;
	private String databaseUsername;
	private String databasePassword;
	private Class<?> bootApplicationClass;
	private UnicodeProperties applicationProperties;
	private UnicodeProperties configProperties;
	public PropertiesFile restorablePropertiesConfig;
	private Consumer<String[]> consumer;
	private int nStartupConsoleLines;

	{
		this.setnStartupConsoleLines(192);
	}
	
	public void init() {
		if (this.getBootApplicationClass() == null)
			throw new IllegalArgumentException("use setBootApplicationClass(BootApplication.class)");
		
		File parentDir = null;
		if (BootUtilsServer.isRunnableJarExecution(BootApplication.class)) {
			parentDir = BootUtilsServer.getRunnableJarExecutionDirectory(BootApplication.class);
			this.setParentDirectory(parentDir.getPath());
		} else {
			this.setParentDirectory(System.getProperty("user.home") +"\\Desktop");
			parentDir = new File(this.getParentDirectory());
		}
		
		
		
		try {
			this.setRestorablePropertiesConfig(new PropertiesFile(
					"config.properties", 
					parentDir +"/phantomData/config/config.properties"));
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		this.setConfigProperties(restorablePropertiesConfig.getProperties());
		
//		
		
		
		this.setHomePath(parentDirectory +"\\phantomData");
//		this.setDatabasePath(homePath +"\\db");
		this.setDatabasePath(homePath +"\\test45db");
		this.setDatabaseFilename("h2");
		this.setDatabaseFileExtension("db");
		this.setFilesPath(homePath +"\\data");
		this.setLogsPath(homePath +"\\logs");
		
//		configProperties.put("database.path", homePath +"\\db");
		
//		System.out.println("config properties: " +configProperties.toString());
		
		try {
			restorablePropertiesConfig.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		this.setDatabasePath((String) configProperties.get("database.path"));
//		System.out.println(configProperties.get("database.path"));
//		
//		System.out.println(parentDir.getAbsolutePath());
//		System.out.println("jdbc:h2:" +parentDir.getAbsolutePath() +"/phantomData/db/phantoms;AUTO_SERVER=TRUE");
		
//		CustomProperties applicationProperties = IOutil.readProperties("application.properties");
		
		
		
		
		
//		InputStream inputStream = null;
//		try {
//			inputStream = IOutil.readExternalFile("D:\\Users\\Marc\\Desktop\\application.properties");
//		} catch (FileNotFoundException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		
//		File file = new File(inputStream);
//		
//		
//		
		
		
		
		UnicodeProperties applicationProperties = new UnicodeProperties();
		PropertiesFile applicationFile = new PropertiesFile();
		applicationFile.setProperties(applicationProperties);
		try {
			// just .../Desktop for classpath 
			applicationFile.setExternalPath("D:/Users/Marc/Desktop/application.properties");
			applicationFile.setResourcePath("application.properties");
			applicationFile.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
//		try {
//			addExternalDirectoryToClassPath("D:/Users/Marc/Desktop");
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}
		
//		try {
//			addURL(new File("D:/Users/Marc/Desktop").toURL());
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}
		
		
//		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
//		
//		if (inputStream == null) System.out.println("null");
//		else
//			System.out.println(inputStream.toString());
		
//		applicationProperties.clear();
		
		
//		Properties properties = new Properties();
//		try {
//			properties.load(inputStream);
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		
		
//		System.out.println("jdbc:h2:" +"D:/Users/Marc/Desktop/phantomData4w/db" +"/phantoms;AUTO_SERVER=TRUE");
//		applicationProperties.remove("spring.datasource.url");
//		
//		applicationProperties.put("spring.datasource.url", "jdbc:h2:" +"D:/Users/Marc/Desktop/phantomData4w/db" +"/phantoms;AUTO_SERVER=TRUE");
		
		
		
//		\\u003A
		
		
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(new File("D:/Users/Marc/Desktop/appp.properties"), false);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Writer writer = new OutputStreamWriter(outputStream);
		
//		try {
//			applicationProperties.store(outputStream, "");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
		
		this.setApplicationProperties(applicationProperties);
		
//		System.out.println("application properties");
//		System.out.println(applicationProperties.toString().replaceAll(", ", "\n"));
		
//		this.setSpringDatasourceUrl(applicationProperties.getProperty("spring.datasource.url"));
//		this.setDatabaseUsername(applicationProperties.getProperty("spring.datasource.username"));
//		this.setDatabasePassword(applicationProperties.getProperty("spring.datasource.password"));
//		
		
		
//		this.setUrlLocalhost("http://localhost:" +applicationProperties.getProperty("server.port"));
		this.setUrlLocalhost("http://localhost:" +"8183");
		
		
		System.out.println("////// application properties ///////////");
//		System.out.println(applicationProperties.toString().replaceAll(", ", "\n"));
	}
	
	// Setters
	private void setFilesPath(String path) {
		this.filesPath = path;
		new File(path).mkdirs();
		PhantomFile.setFilesPath(filesPath);
	}
	
	protected void setUrlLocalhost(String urlLocalhost) {
		this.urlLocalhost = urlLocalhost;
		this.setUrlShutdownActuator(urlLocalhost +"/actuator/shutdown");
	}

	protected void setUrlShutdownActuator(String urlShutdownActuator) {
		this.urlShutdownActuator = urlShutdownActuator;
	}
	
	public void setConsumer(Consumer<String[]> consumer) {
		this.consumer = consumer;
	}
	
	private void setParentDirectory(String path) {
		this.parentDirectory = path;
		new File(path).mkdirs();
	}
	
	private void setHomePath(String path) {
		this.homePath = path;
		new File(path).mkdirs();
	}

	private void setDatabasePath(String path) {
		this.databasePath = path;
		new File(path).mkdirs();
	}
	
	private void setRestorablePropertiesConfig(PropertiesFile restorablePropertiesConfig) {
		this.restorablePropertiesConfig = restorablePropertiesConfig;
	}


	private void setSpringDatasourceUrl(String springDatasourceUrl) {
		this.springDatasourceUrl = springDatasourceUrl;
	}


	private void setLogsPath(String path) {
		this.logsPath = path;
		new File(path).mkdirs();
	}
	
	private void setConfigProperties(UnicodeProperties configProperties) {
		this.configProperties = configProperties;
		
		
	}
	
	private void setDatabaseFilename(String databaseFilename) {
		this.databaseFilename = databaseFilename;
	}


	private void setDatabaseFileExtension(String databaseFileExtension) {
		this.databaseFileExtension = databaseFileExtension;
	}


	private void setDatabaseUsername(String databaseUsername) {
		this.databaseUsername = databaseUsername;
	}


	private void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}


	protected void setBootApplicationClass(Class<?> bootApplicationClass) {
		if (this.bootApplicationClass != null) throw new UnsupportedOperationException();
		this.bootApplicationClass = bootApplicationClass;
	}


	private void setApplicationProperties(UnicodeProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
		
	}


	private void setnStartupConsoleLines(int nStartupConsoleLines) {
		this.nStartupConsoleLines = nStartupConsoleLines;
	}
	
	// Getters
	public String getUrlLocalhost() {
		return urlLocalhost;
	}
	
	public String getDatabasePath() {
		return databasePath;
	}
	
	public String getFilesPath() {
		return filesPath;
	}
	
	public String getLogsPath() {
		return logsPath;
	}
	
	public UnicodeProperties getConfigProperties() {
		return configProperties;
	}
	
	public PropertiesFile getRestorablePropertiesConfig() {
		return restorablePropertiesConfig;
	}
	
	public String getParentDirectory() {
		return parentDirectory;
	}
	
	public String getHomePath() {
		return homePath;
	}
	
	public String getDatabaseFilename() {
		return databaseFilename;
	}
	
	public String getDatabaseFileExtension() {
		return databaseFileExtension;
	}
	
	public String getUrlShutdownActuator() {
		return urlShutdownActuator;
	}
	
	public String getSpringDatasourceUrl() {
		return springDatasourceUrl;
	}
	
	public String getDatabaseUsername() {
		return databaseUsername;
	}
	
	public String getDatabasePassword() {
		return databasePassword;
	}
	
	public Class<?> getBootApplicationClass() {
		return bootApplicationClass;
	}
	
	public UnicodeProperties getApplicationProperties() {
		return applicationProperties;
	}
	
	public Consumer<String[]> getConsumer() {
		return consumer;
	}

	public int getnStartupConsoleLines() {
		return nStartupConsoleLines;
	}
	
	
}
