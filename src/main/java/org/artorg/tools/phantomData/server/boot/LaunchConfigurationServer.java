package org.artorg.tools.phantomData.server.boot;

import java.io.File;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.artorg.tools.phantomData.server.io.IOutil;
import org.artorg.tools.phantomData.server.io.PropertiesFile;
import org.artorg.tools.phantomData.server.io.PropertyPut;

public abstract class LaunchConfigurationServer {
	private String parentDirectory;
	private String configPath;
	private PropertiesFile applicationFile;
	private PropertiesFile configFile;
	private Class<?> bootApplicationClass;
	public void setBootApplicationClass(Class<?> bootApplicationClass) {
		this.bootApplicationClass = bootApplicationClass;
	}

	private boolean externalConfigOverridable;
	public boolean isExternalConfigOverridable() {
		return externalConfigOverridable;
	}

	private boolean serverStartedEmbedded;
	private boolean initialized;
	
	public boolean isInitialized() {
		return initialized;
	}

	protected void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	{
		serverStartedEmbedded = false;
		initialized = false;
	}
	
	public boolean isServerStartedEmbedded() {
		return serverStartedEmbedded;
	}

	public void setServerStartedEmbedded(boolean serverStartedEmbedded) {
		this.serverStartedEmbedded = serverStartedEmbedded;
	}

	public LaunchConfigurationServer(Class<?> bootApplicationClass, boolean externalConfigOverridable) {
		this.bootApplicationClass = bootApplicationClass;
		this.externalConfigOverridable = externalConfigOverridable;
	}
	
	public LaunchConfigurationServer() {}
	
	public void init() {
		if (!initialized) {
			if (isRunnableJarExecution()) {
				File parentDir = getRunnableJarExecutionDirectory();
				parentDirectory = parentDir.getPath().replace("\\", "/");
			} else {
				parentDirectory = System.getProperty("user.home").replace("\\", "/") +"/Desktop";
			}
			
			configPath = parentDirectory +"/phantomData/config";
			
			PropertyPut[] configPuts = new PropertyPut[] {
				new PropertyPut("parent.directory.path", parentDirectory),
				new PropertyPut("home.path", parentDirectory +"/phantomData"),
				new PropertyPut("database.path", parentDirectory + "/phantomData/db"),
				new PropertyPut("database.name.file", "h2"),
				new PropertyPut("database.name.ext", "db"),
				new PropertyPut("files.path", parentDirectory +"/phantomData/data"),
				new PropertyPut("logs.path", parentDirectory +"/phantomData/logs"),
				new PropertyPut("localhost.url", "http://localhost:" + "8183"),
				new PropertyPut("shutdown.actuator.url", "http://localhost:" + "8183" +"/actuator/shutdown"),
				new PropertyPut("debug.console.mode", "false")
			};
			configFile = new PropertiesFile(getConfigPath() +"/config.properties", configPuts, externalConfigOverridable);
	
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
			applicationFile = new PropertiesFile(getConfigPath() +"/application.properties", applicationPuts, externalConfigOverridable);
			
			try {
				IOutil.addExternalDirectoryToClassPath(configPath +"/");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			initialized = true;
		}
	}
	
	public boolean isRunnableJarExecution() {
		String uriPath = null;
		try {
			uriPath = getBootApplicationClass().getProtectionDomain().getCodeSource().getLocation().toURI().toString();
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
	
	public File getRunnableJarExecutionDirectory() {
		String path = null;
		
		try {
			path = getBootApplicationClass().getProtectionDomain().getCodeSource().getLocation().toURI().toString();
		} catch (URISyntaxException e2) {
			e2.printStackTrace();
		}
		
		Pattern pattern = Pattern.compile("jar:file:/(.*)");
		Matcher m = pattern.matcher(path);
		File file;
		if(m.find())
			file = new File(m.group(1));
		else 
			throw new IllegalArgumentException();
		
		while (file.getPath().contains(".jar"))
			file = file.getParentFile();

		return file;
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
	
	public void setExternalConfigOverridable(boolean b) {
		this.externalConfigOverridable = b;
	}
	
	// Getters - properties - config
	public String getHomePath() {
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
	
	public boolean isDebugConsoleMode() {
		return Boolean.valueOf(configFile.getProperty("debug.console.mode"));
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
	
	public String getPort() {
		return applicationFile.getProperty("server.port");
	}

}
