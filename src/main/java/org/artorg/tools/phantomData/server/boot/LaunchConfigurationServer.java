package org.artorg.tools.phantomData.server.boot;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.Consumer;

import org.artorg.tools.phantomData.server.BootApplication;
import org.artorg.tools.phantomData.server.io.PropertiesFile;
import org.artorg.tools.phantomData.server.io.PropertyPut;
import org.artorg.tools.phantomData.server.io.UnicodeProperties;
import org.artorg.tools.phantomData.server.model.PhantomFile;

public class LaunchConfigurationServer {
	private int nStartupConsoleLines;
	private String parentDirectory;
	private String configPath;
	private PropertiesFile applicationFile;
	private PropertiesFile configFile;
	
	private Class<?> bootApplicationClass;
	private Consumer<String[]> consumer;
	private boolean configurationHidden = false;
	private boolean externalConfigOverride = false;
	
	
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
			String test = System.getProperty("user.home").replace("\\", "/");
			parentDirectory = test +"/Desktop";
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
		configFile = createProperties("src/main/resources/config.properties", 
				getConfigPath() +"/config.properties", 
				configPuts);

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
		applicationFile = createProperties("src/main/resources/application.properties", 
				getConfigPath() +"/application.properties", 
				applicationPuts);
		
		new File(getParentDirectory()).mkdirs();
		new File(getHomePath()).mkdirs();
		new File(getDatabasePath()).mkdirs();
		new File(getLogsPath()).mkdirs();
		new File(getFilesPath()).mkdirs();
		
		PhantomFile.setFilesPath(getFilesPath());
		
	}
	
	private PropertiesFile createProperties(String resourcePath, String externalPath, PropertyPut[] propertyPuts) {
		PropertiesFile propertiesFile = new PropertiesFile();
		UnicodeProperties properties = propertiesFile.getProperties();
		try {
			propertiesFile.setResourcePath(resourcePath);
			propertiesFile.setExternalPath(externalPath);
			propertiesFile.setProperties(properties);
			
			if (propertiesFile.existExternal()) {
				if (externalConfigOverride) {
					Arrays.stream(propertyPuts).forEach(put -> {
						properties.put(put.getKey(), put.getValue());
					});
					propertiesFile.writeExternal();
				} else {
					propertiesFile.readExternal();
//					Arrays.stream(propertyPuts).forEach(put -> {
//						String value = properties.getProperty(put.getKey());
//						properties.put(put.getKey(), value);
//					});
				}
			} else {
				if (configurationHidden) {
					Arrays.stream(propertyPuts).forEach(put -> {
						properties.put(put.getKey(), put.getValue());
					});
				} else {
					propertiesFile.createExternal();
					Arrays.stream(propertyPuts).forEach(put -> {
						properties.put(put.getKey(), put.getValue());
					});
					propertiesFile.writeExternal();
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (!BootUtilsServer.isRunnableJarExecution(getClass())) {
			try {
				propertiesFile.writeResource();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		return propertiesFile;
	}
	
	// Setters
	public void setConsumer(Consumer<String[]> consumer) {
		this.consumer = consumer;
	}
	
	private void setConfigPath(String configpath) {
		this.configPath = configpath;
	}

	protected void setBootApplicationClass(Class<?> bootApplicationClass) {
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
		return configFile.getResourceProperty("home.path");
	}
	
	public String getDatabasePath() {
		return configFile.getResourceProperty("database.path");
	}
	
	public String getDatabaseFilename() {
		return configFile.getResourceProperty("database.name.file");
	}
	
	public String getDatabaseFileExtension() {
		return configFile.getResourceProperty("database.name.ext");
	}
	
	public String getUrlLocalhost() {
		return configFile.getResourceProperty("localhost.url");
	}
	
	public String getUrlShutdownActuator() {
		return configFile.getResourceProperty("shutdown.actuator.url");
	}
	
	public String getFilesPath() {
		return configFile.getResourceProperty("files.path");
	}
	
	public String getLogsPath() {
		return configFile.getResourceProperty("logs.path");
	}
	
	// Getters - properties - application
	public String getSpringDatasourceUrl() {
		return applicationFile.getResourceProperty("spring.datasource.url");
	}
	
	public String getDatabaseUsername() {
		return applicationFile.getResourceProperty("spring.datasource.username");
	}
	
	public String getDatabasePassword() {
		return applicationFile.getResourceProperty("spring.datasource.password");
	}
	
	public String getSqlDriver() {
		return applicationFile.getResourceProperty("sql.driver");
	}

}
