package org.artorg.tools.phantomData.server.boot;

import java.io.File;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import huma.io.IOutil;
import huma.io.PropertiesFile;

/**
 * Booter with two external configuration files. Call {@link #init()} for
 * booting. The path of the two configuration files cannot changed by a setting. The
 * files will be searched in {@code parentDirectory/phantomData/config}. The parent
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
			
			@SuppressWarnings("unchecked")
			SimpleEntry<String,String>[] configPuts = new SimpleEntry[] {
					new SimpleEntry<>("parent.directory.path", parentDirectory),
					new SimpleEntry<>("home.path", parentDirectory + "/phantomData"),
					new SimpleEntry<>("database.path", parentDirectory + "/phantomData/db"),
					new SimpleEntry<>("database.name.file", "h2"),
					new SimpleEntry<>("database.name.ext", "db"),
					new SimpleEntry<>("files.path", parentDirectory + "/phantomData/data"),
					new SimpleEntry<>("logs.path", parentDirectory + "/phantomData/logs"),
					new SimpleEntry<>("localhost.url", "http://localhost:" + "8183"),
					new SimpleEntry<>("shutdown.actuator.url",
							"http://localhost:" + "8183" + "/actuator/shutdown"),
					new SimpleEntry<>("debug.console.mode", "false") };
			configFile = new PropertiesFile(getConfigPath() + "/config.properties",
					configPuts, externalConfigOverridable);
			
			@SuppressWarnings("unchecked")
			SimpleEntry<String,String>[] applicationPuts = new SimpleEntry[] {
					new SimpleEntry<>("spring.datasource.hikari.connection-timeout",
							"20000"),
					new SimpleEntry<>("spring.jpa.properties.hibernate.dialect",
							"org.hibernate.dialect.MySQL5Dialect"),
					new SimpleEntry<>("spring.datasource.url",
							"jdbc:h2:" + parentDirectory
									+ "/phantomData/db/h2;AUTO_SERVER=TRUE"),
					new SimpleEntry<>("endpoints.shutdown.sensitive", "false"),
					new SimpleEntry<>("spring.datasource.password", "1234"),
					new SimpleEntry<>("spring.jpa.properties.hibernate.format_sql", "true"),
					new SimpleEntry<>("management.endpoint.shutdown.enabled", "true"),
					new SimpleEntry<>("spring.h2.console.enabled", "true"),
					new SimpleEntry<>("management.endpoints.web.exposure.include", "*"),
					new SimpleEntry<>("spring.datasource.username", "admin"),
					new SimpleEntry<>(
							"spring.jpa.properties.hibernate.id.new_generator_mappings",
							"false"),
					new SimpleEntry<>("endpoints.shutdown.enabled", "true"),
					new SimpleEntry<>("sql.driver", "org.h2.Driver"),
					new SimpleEntry<>("spring.datasource.hikari.maximum-pool-size", "12"),
					new SimpleEntry<>("server.port", "8183"),
					new SimpleEntry<>("spring.jpa.hibernate.ddl-auto", "update"),
					new SimpleEntry<>("spring.datasource.hikari.max-lifetime", "1200000"),
					new SimpleEntry<>("spring.datasource.hikari.minimum-idle", "5"),
					new SimpleEntry<>("spring.datasource.hikari.idle-timeout", "300000") };
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
	 * @return Directory of the configuration files.
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
