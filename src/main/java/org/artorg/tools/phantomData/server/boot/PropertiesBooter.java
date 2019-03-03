package org.artorg.tools.phantomData.server.boot;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import huma.io.IOutil;
import huma.io.PropertiesFile;
import huma.io.PropertyPut;

/**
 * Booter with two external configuration files. Call {@link #init()} for
 * booting. The path of the two config files cannot changed by a setting. The
 * files will be searched in parentDirectory/phantomData/config. The parent
 * directory is the directory where the runnable *.jar file is placed.
 * 
 * <h3>application.properties</h3> Contains settings for Spring and the
 * database.
 * 
 * <h3>config.properties</h3> Contains general settings whose are defined in
 * this project. The are not given by frameworks.
 * 
 * @see PropertiesFile
 */
public abstract class PropertiesBooter extends Booter {
	private String parentDirectory;
	private String configPath;
	private PropertiesFile applicationFile;
	private PropertiesFile configFile;
	private boolean externalConfigOverridable;
	private boolean initialized;
	
	{
		initialized = false;
	}
	
	public void init() {
		if (!initialized) {
			if (isWindowsOs()) {
				if (isRunnableJarExecution()) {
					File parentDir = getRunnableJarExecutionDirectory();
					String path = parentDir.getPath().replace("\\", "/");
					parentDirectory = path;
				} else {
					parentDirectory = System.getProperty("user.home").replace("\\", "/")
							+ "/Desktop";
				}
				configPath = parentDirectory + "/phantomData/config";
			} else if (isLinuxOs() || isMacOs()) {
				if (isRunnableJarExecution()) {
					File parentDir = getRunnableJarExecutionDirectory();
					String path = parentDir.getPath();
					if (path.matches("(?i).*zonula.*")
							|| path.matches("(?i).*sclera.*")) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame,
								"On unix machines application has to be started local!\n"
										+ "Read install instructions for informations.");
						frame.dispose();
						System.exit(0);
					}
					parentDirectory = path;
				} else {
					new UnsupportedOperationException().printStackTrace();
				}
				configPath = parentDirectory + "/phantomData/config";
			} else {
				new UnsupportedOperationException().printStackTrace();
			}
			
			PropertyPut[] configPuts = new PropertyPut[] {
					new PropertyPut("parent.directory.path", parentDirectory),
					new PropertyPut("home.path", parentDirectory + "/phantomData"),
					new PropertyPut("database.path", parentDirectory + "/phantomData/db"),
					new PropertyPut("database.name.file", "h2"),
					new PropertyPut("database.name.ext", "db"),
					new PropertyPut("files.path", parentDirectory + "/phantomData/data"),
					new PropertyPut("logs.path", parentDirectory + "/phantomData/logs"),
					new PropertyPut("localhost.url", "http://localhost:" + "8183"),
					new PropertyPut("shutdown.actuator.url",
							"http://localhost:" + "8183" + "/actuator/shutdown"),
					new PropertyPut("debug.console.mode", "false") };
			configFile = new PropertiesFile(getConfigPath() + "/config.properties",
					configPuts, externalConfigOverridable);
			
			PropertyPut[] applicationPuts = new PropertyPut[] {
					new PropertyPut("spring.datasource.hikari.connection-timeout",
							"20000"),
					new PropertyPut("spring.jpa.properties.hibernate.dialect",
							"org.hibernate.dialect.MySQL5Dialect"),
					new PropertyPut("spring.datasource.url",
							"jdbc:h2:" + parentDirectory
									+ "/phantomData/db/h2;AUTO_SERVER=TRUE"),
					new PropertyPut("endpoints.shutdown.sensitive", "false"),
					new PropertyPut("spring.datasource.password", "1234"),
					new PropertyPut("spring.jpa.properties.hibernate.format_sql", "true"),
					new PropertyPut("management.endpoint.shutdown.enabled", "true"),
					new PropertyPut("spring.h2.console.enabled", "true"),
					new PropertyPut("management.endpoints.web.exposure.include", "*"),
					new PropertyPut("spring.datasource.username", "admin"),
					new PropertyPut(
							"spring.jpa.properties.hibernate.id.new_generator_mappings",
							"false"),
					new PropertyPut("endpoints.shutdown.enabled", "true"),
					new PropertyPut("sql.driver", "org.h2.Driver"),
					new PropertyPut("spring.datasource.hikari.maximum-pool-size", "12"),
					new PropertyPut("server.port", "8183"),
					new PropertyPut("spring.jpa.hibernate.ddl-auto", "update"),
					new PropertyPut("spring.datasource.hikari.max-lifetime", "1200000"),
					new PropertyPut("spring.datasource.hikari.minimum-idle", "5"),
					new PropertyPut("spring.datasource.hikari.idle-timeout", "300000") };
			applicationFile = new PropertiesFile(
					getConfigPath() + "/application.properties", applicationPuts,
					externalConfigOverridable);
			
			try {
				IOutil.addExternalDirectoryToClassPath(configPath + "/");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			initialized = true;
		}
		
	}
	
	public void setExternalConfigOverridable(boolean b) {
		externalConfigOverridable = b;
	}
	
	public boolean isInitialized() {
		return initialized;
	}
	
	protected void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
	
	public boolean isExternalConfigOverridable() {
		return externalConfigOverridable;
	}
	
	/**
	 * @return Directory where the runnable *.jar file is placed.
	 */
	public String getParentDirectory() {
		return parentDirectory;
	}
	
	/**
	 * @return Directory of the config files.
	 */
	public String getConfigPath() {
		return configPath;
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
