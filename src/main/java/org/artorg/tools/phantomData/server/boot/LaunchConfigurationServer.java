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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.artorg.tools.phantomData.server.BootApplication;
import org.artorg.tools.phantomData.server.io.IOutil;
import org.artorg.tools.phantomData.server.io.PropertiesFile;
import org.artorg.tools.phantomData.server.io.UnicodeProperties;
import org.artorg.tools.phantomData.server.model.PhantomFile;

import static org.artorg.tools.phantomData.server.io.IOutil.*;

public class LaunchConfigurationServer {
	private int nStartupConsoleLines;
	private String urlLocalhost;
	private String parentDirectory;
	private String databasePath;
	private String configPath;
	private String filesPath;
	private PropertiesFile applicationFile;
	private PropertiesFile configFile;
	
	private Class<?> bootApplicationClass;
	private Consumer<String[]> consumer;
	
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
			new PropertyPut("shutdown.actuator.url", "http://localhost:" + "8183" +"/actuator/shutdown")
		};
		configFile = createProperties("src/main/resources/config.properties", 
				() -> getConfigPath() +"/config.properties", 
				configPuts);
		try {
			configFile.writeResource();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

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
				() -> getConfigPath() +"/application.properties", 
				applicationPuts);
		try {
			applicationFile.writeResource();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new File(getParentDirectory()).mkdirs();
		new File(getHomePath()).mkdirs();
		new File(getDatabasePath()).mkdirs();
		new File(getLogsPath()).mkdirs();
		
	}
	
	private class PropertyPut {
		private final String key;
		private final String value;
		PropertyPut(String key, String value) {
			this.key = key;
			this.value = value;
		}
		public String getKey() {return key;}
		public String getValue() {return value;}
	}
	
	private PropertiesFile createProperties(String resourcePath, Supplier<String> externalPath, PropertyPut[] propertyPuts) {
		PropertiesFile propertiesFile = new PropertiesFile();
		UnicodeProperties properties = propertiesFile.getProperties();
//		Arrays.stream(propertyPuts).forEach(put -> put.getSetter().accept(put.getValue().get()));
		
		try {
			propertiesFile.setResourcePath(resourcePath);
			propertiesFile.setExternalPath(externalPath.get());
			propertiesFile.setProperties(properties);
			
			if (propertiesFile.existExternal()) {
				propertiesFile.readExternal();
				Arrays.stream(propertyPuts).forEach(put -> {
					String value = properties.getProperty(put.getKey());
					properties.put(put.getKey(), value);
					
//					putProperty(properties, put.getKey(), value, put.getSetter());
				});
			} else {
				propertiesFile.createExternal();
				Arrays.stream(propertyPuts).forEach(put -> {
					properties.put(put.getKey(), put.getValue());
//					putProperty(properties, put.getKey(), put.getValue().get(), put.getSetter());
				});
				propertiesFile.writeExternal();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return propertiesFile;
	}
	
	
	private String getResourceProperty(PropertiesFile propertiesFile, String key) {
		String resourcePath = propertiesFile.getResourcePath();
		String value = null;
		String resourceFilename = "config.properties";
		try {
			value = IOutil.readProperties(resourceFilename).getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (value == null) throw new NullPointerException("key: " +key +", resourcePath: " +resourcePath);
		return value;
	}
	
	// Setters
	private void setFilesPath(String path) {
		this.filesPath = path;
		new File(path).mkdirs();
		PhantomFile.setFilesPath(filesPath);
	}
	
//	protected void setUrlLocalhost(String urlLocalhost) {
//		this.urlLocalhost = urlLocalhost;
//		this.setUrlShutdownActuator(urlLocalhost +"/actuator/shutdown");
//	}
//
//	protected void setUrlShutdownActuator(String urlShutdownActuator) {
//		this.urlShutdownActuator = urlShutdownActuator;
//	}
	
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
	public String getUrlLocalhost() {
		return urlLocalhost;
//		return IOutil.readProperties("application.properties").getProperty("server.port");
	}
	
	public String getDatabasePath() {
//		return databasePath;
		return getResourceProperty(applicationFile, "database.path");
		
//		return IOutil.readProperties("config.properties").getProperty("database.path");
	}
	
	public String getFilesPath() {
		return getResourceProperty(applicationFile, "files.path");
	}
	
	public String getLogsPath() {
		return getResourceProperty(applicationFile, "logs.path");
	}
	
	public String getParentDirectory() {
		return parentDirectory;
//		return IOutil.readProperties("config.properties").getProperty("parent.directory.path");
	}
	
	private String getHomePath() {
		return getResourceProperty(configFile, "home.path");
	}
	
	public String getDatabaseFilename() {
		return getResourceProperty(configFile, "database.name.file");
	}
	
	public String getDatabaseFileExtension() {
		return getResourceProperty(configFile, "database.name.ext");
	}
	
	public String getUrlShutdownActuator() {
		return getResourceProperty(configFile, "shutdown.actuator.url");
		
//		return urlShutdownActuator;
//		return getUrlLocalhost() +"/actuator/shutdown";
		
//		return IOutil.readProperties("application.properties").getProperty("database.name.ext");
	}
	
	public String getSpringDatasourceUrl() {
		return getResourceProperty(applicationFile, "spring.datasource.url");
	}
	
	public String getDatabaseUsername() {
		return getResourceProperty(applicationFile, "spring.datasource.username");
	}
	
	public String getDatabasePassword() {
		return getResourceProperty(applicationFile, "spring.datasource.password");
	}
	
	public String getConfigPath() {
		return configPath;
	}

	public String getSqlDriver() {
		return getResourceProperty(applicationFile, "sql.driver");
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
	
	
}
