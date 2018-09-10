package org.artorg.tools.phantomData.server.boot.util;

import java.io.File;
import java.util.function.Consumer;

import org.artorg.tools.phantomData.server.BootApplication;
import org.artorg.tools.phantomData.server.io.IOutil;
import org.artorg.tools.phantomData.server.io.PropertiesFile;
import org.artorg.tools.phantomData.server.io.PropertyPut;
import org.artorg.tools.phantomData.server.model.PhantomFile;

public class LaunchConfigurationServer {
	private int nStartupConsoleLines;
	private String parentDirectory;
	private String configPath;
	private PropertiesFile applicationFile;
	private PropertiesFile configFile;
	
	private Class<?> bootApplicationClass;
	private Consumer<String[]> consumer;
	private boolean externalConfigOverride = true;
	
	public boolean isExternalConfigOverride() {
		return externalConfigOverride;
	}

	public void setExternalConfigOverride(boolean externalConfigOverride) {
		this.externalConfigOverride = externalConfigOverride;
	}

	{
		this.setnStartupConsoleLines(192);
	}
	
	public void init() {
		if (this.getBootApplicationClass() == null)
			throw new IllegalArgumentException("use setBootApplicationClass(BootApplication.class)");
		
		if (BootUtilsServer.isRunnableJarExecution(BootApplication.class)) {
			File parentDir = BootUtilsServer.getRunnableJarExecutionDirectory(BootApplication.class);
			parentDirectory = parentDir.getPath().replace("\\", "/");
		} else {
			parentDirectory = System.getProperty("user.home").replace("\\", "/") +"/Desktop";
		}
		
		this.setConfigPath(parentDirectory +"/phantomData/config");
		
		PropertyPut[] configPuts = new PropertyPut[] {
			new PropertyPut("parent.directory.path", parentDirectory),
			new PropertyPut("home.path", parentDirectory +"/phantomData"),
			new PropertyPut("database.path", parentDirectory + "/phantomData/db"),
			new PropertyPut("database.name.file", "h2"),
			new PropertyPut("database.name.ext", "db"),
			new PropertyPut("files.path", parentDirectory +"/phantomData/data"),
			new PropertyPut("logs.path", parentDirectory +"/phantomData/logs"),
			new PropertyPut("localhost.url", "http://localhost:" + "8183"),
			new PropertyPut("shutdown.actuator.url", "http://localhost:" + "8183" +"/actuator/shutdown")
		};
		configFile = new PropertiesFile(getConfigPath() +"/config.properties", configPuts, externalConfigOverride);

		PropertyPut[] applicationPuts = new PropertyPut[] {
			new PropertyPut("spring.datasource.hikari.connection-timeout", "20000"),
			new PropertyPut("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect"),
			new PropertyPut("spring.datasource.url", 
				"jdbc:h2:" +parentDirectory + "/phantomData/db/h2;AUTO_SERVER=TRUE"),
			new PropertyPut("endpoints.shutdown.sensitive", "false"),
			new PropertyPut("spring.datasource.password", "1234"),
			new PropertyPut("spring.jpa.properties.hibernate.format_sql", "true"),
			new PropertyPut("management.endpoint.shutdown.enabled", "true"),
			new PropertyPut("spring.h2.console.enabled", "true"),
			new PropertyPut("management.endpoints.web.exposure.include", "*"),
			new PropertyPut("spring.datasource.username", "admin"),
			new PropertyPut("spring.jpa.properties.hibernate.id.new_generator_mappings", "false"),
			new PropertyPut("endpoints.shutdown.enabled", "true"),
			new PropertyPut("sql.driver", "org.h2.Driver"),
			new PropertyPut("spring.datasource.hikari.maximum-pool-size", "12"),
			new PropertyPut("server.port", "8183"),
			new PropertyPut("spring.jpa.hibernate.ddl-auto", "update"),
			new PropertyPut("spring.datasource.hikari.max-lifetime", "1200000"),
			new PropertyPut("spring.datasource.hikari.minimum-idle", "5"),
			new PropertyPut("spring.datasource.hikari.idle-timeout", "300000")
		};
		applicationFile = new PropertiesFile(getConfigPath() +"/application.properties", applicationPuts, externalConfigOverride);
		
		try {
			IOutil.addExternalDirectoryToClassPath(configPath +"/");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new File(getParentDirectory()).mkdirs();
		new File(getHomePath()).mkdirs();
		new File(getDatabasePath()).mkdirs();
		new File(getLogsPath()).mkdirs();
		new File(getFilesPath()).mkdirs();
		
		PhantomFile.setFilesPath(getFilesPath());
		
	}
	
	// Setters
	public void setConsumer(Consumer<String[]> consumer) {
		this.consumer = consumer;
	}
	
	private void setConfigPath(String configpath) {
		this.configPath = configpath;
	}

	public void setBootApplicationClass(Class<?> bootApplicationClass) {
		if (this.bootApplicationClass != null) throw new UnsupportedOperationException();
		this.bootApplicationClass = bootApplicationClass;
	}
	
	private void setnStartupConsoleLines(int nStartupConsoleLines) {
		this.nStartupConsoleLines = nStartupConsoleLines;
	}
	
	// Getters
	public String getParentDirectory() {
		return parentDirectory;
	}
	
	public String getConfigPath() {
		return configPath;
	}
	
	public Class<?> getBootApplicationClass() {
		return bootApplicationClass;
	}
	
	public Consumer<String[]> getConsumer() {
		return consumer;
	}

	public int getnStartupConsoleLines() {
		return nStartupConsoleLines;
	}
	
	// Getters - properties - config
	private String getHomePath() {
		return configFile.getProperty("home.path");
	}
	
	public String getDatabasePath() {
		return configFile.getProperty("database.path");
	}
	
	public String getDatabaseFilename() {
		return configFile.getProperty("database.name.file");
	}
	
	public String getDatabaseFileExtension() {
		return configFile.getProperty("database.name.ext");
	}
	
	public String getUrlLocalhost() {
		return configFile.getProperty("localhost.url");
	}
	
	public String getUrlShutdownActuator() {
		return configFile.getProperty("shutdown.actuator.url");
	}
	
	public String getFilesPath() {
		return configFile.getProperty("files.path");
	}
	
	public String getLogsPath() {
		return configFile.getProperty("logs.path");
	}
	
	// Getters - properties - application
	public String getSpringDatasourceUrl() {
		return applicationFile.getProperty("spring.datasource.url");
	}
	
	public String getDatabaseUsername() {
		return applicationFile.getProperty("spring.datasource.username");
	}
	
	public String getDatabasePassword() {
		return applicationFile.getProperty("spring.datasource.password");
	}
	
	public String getSqlDriver() {
		return applicationFile.getProperty("sql.driver");
	}

}
