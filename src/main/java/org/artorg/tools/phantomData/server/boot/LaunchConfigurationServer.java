package org.artorg.tools.phantomData.server.boot;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.function.Consumer;

import org.artorg.tools.phantomData.server.BootApplication;
import org.artorg.tools.phantomData.server.io.ResourceReader;
import org.artorg.tools.phantomData.server.io.RestorablePropertiesFile;
import org.artorg.tools.phantomData.server.model.PhantomFile;

public class LaunchConfigurationServer {
	private String urlLocalhost;
	private String urlShutdownActuator;
	private String homePath;
	private String databasePath;
	private String filesPath;
	private String logsPath;
	private String databaseFilename;
	private String databaseFileExtension;
	private String springDatasourceUrl;
	private String databaseUsername;
	private String databasePassword;
	private Class<?> mainClass;
	private Properties applicationProperties;
	private Properties configProperties;
	public RestorablePropertiesFile restorablePropertiesConfig;
	private Consumer<String[]> consumer;
	private int nStartupConsoleLines;

	{
		this.setnStartupConsoleLines(192);
	}
	
	public void init(Class<?> bootApplicationClass) {
		this.setMainClass(bootApplicationClass);
		
		
		File parentDir = null;
		if (BootUtilsServer.isRunnableJarExecution(BootApplication.class)) {
			parentDir = BootUtilsServer.getRunnableJarExecutionDirectory(BootApplication.class);
		} else {
			homePath = System.getProperty("user.home") +"\\Desktop\\";
			parentDir = new File(homePath);
		}
		
		Properties applicationProperties = ResourceReader.readProperties("application.properties", this.getMainClass());
		applicationProperties.remove("spring.datasource.url");
		applicationProperties.put("spring.datasource.url", "jdbc:h2:" +parentDir.getAbsolutePath() +"\\phantomData\\db\\phantoms;AUTO_SERVER=TRUE");
		this.setApplicationProperties(applicationProperties);
		
		this.setRestorablePropertiesConfig(new RestorablePropertiesFile(
				"config.properties", 
				parentDir +"\\phantomData\\config\\config.properties",
				this.getMainClass()));
		this.setConfigProperties(restorablePropertiesConfig.getProperties());
		
		this.setDatabaseFilename(configProperties.getProperty("database.name.file"));
		this.setDatabaseFileExtension(configProperties.getProperty("database.name.ext"));
		
		this.setSpringDatasourceUrl(applicationProperties.getProperty("spring.datasource.url"));
		this.setDatabaseUsername(applicationProperties.getProperty("spring.datasource.username"));
		this.setDatabasePassword(applicationProperties.getProperty("spring.datasource.password"));
		
		this.setUrlLocalhost("http://localhost:" +getApplicationProperties().getProperty("server.port"));
		this.setUrlShutdownActuator(urlLocalhost +"/actuator/shutdown");
		this.setDatabasePath(configProperties.getProperty("database.path"));
		this.setFilesPath(configProperties.getProperty("files.path"));
		this.setHomePath(configProperties.getProperty("home.path"));
		this.setLogsPath(configProperties.getProperty("log.path"));
		
	}
	
	// Setters
	private void setFilesPath(String filesPath) {
		this.filesPath = filesPath;
		PhantomFile.setFilesPath(filesPath);
	}
	
	protected void setUrlLocalhost(String urlLocalhost) {
		this.urlLocalhost = urlLocalhost;
	}

	protected void setUrlShutdownActuator(String urlShutdownActuator) {
		this.urlShutdownActuator = urlShutdownActuator;
	}
	
	public void setConsumer(Consumer<String[]> consumer) {
		this.consumer = consumer;
	}
	
	private void setHomePath(String homePath) {
		this.homePath = homePath;
	}


	private void setDatabasePath(String databasePath) {
		this.databasePath = databasePath;
	}
	
	private void setRestorablePropertiesConfig(RestorablePropertiesFile restorablePropertiesConfig) {
		this.restorablePropertiesConfig = restorablePropertiesConfig;
		try {
			restorablePropertiesConfig.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void setSpringDatasourceUrl(String springDatasourceUrl) {
		this.springDatasourceUrl = springDatasourceUrl;
	}


	private void setLogsPath(String logsPath) {
		this.logsPath = logsPath;
	}
	
	private void setConfigProperties(Properties configProperties) {
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


	protected void setMainClass(Class<?> mainClass) {
		this.mainClass = mainClass;
	}


	private void setApplicationProperties(Properties applicationProperties) {
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
	
	public Properties getConfigProperties() {
		return configProperties;
	}
	
	public RestorablePropertiesFile getRestorablePropertiesConfig() {
		return restorablePropertiesConfig;
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
	
	public Class<?> getMainClass() {
		return mainClass;
	}
	
	public Properties getApplicationProperties() {
		return applicationProperties;
	}
	
	public Consumer<String[]> getConsumer() {
		return consumer;
	}

	public int getnStartupConsoleLines() {
		return nStartupConsoleLines;
	}
	
	
}
