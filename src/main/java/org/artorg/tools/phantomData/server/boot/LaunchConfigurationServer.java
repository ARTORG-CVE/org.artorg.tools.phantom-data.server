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
import java.util.ArrayList;
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
	private String urlLocalhost;
	private String urlShutdownActuator;
	private String parentDirectory;
	private String databasePath;
	private String homePath;
	private String configPath;
	
	
	public String getConfigPath() {
		return configPath;
	}


	

	private String sqlDriver;
	public String getSqlDriver() {
		return sqlDriver;
	}


	

	private String filesPath;
	private String logsPath;
	private String databaseFilename;
	private String databaseFileExtension;
	private String springDatasourceUrl;
	private String databaseUsername;
	private String databasePassword;
	private Class<?> bootApplicationClass;
	private Consumer<String[]> consumer;
	private int nStartupConsoleLines;

	{
		this.setnStartupConsoleLines(192);
	}
	
	private void putProperty(UnicodeProperties properties, String key, String value, Consumer<String> setter) {
		properties.put(key, value);
		setter.accept(value);
		Map<String,String> map =  new HashMap<String,String>();
		if (map.containsKey(key))
			map.replace(key, value);
		else 
			map.put(key, value);
	}
	
	private class PropertyPut {
		private final String key;
		private final Supplier<String> value;
		private final Consumer<String> setter;
		PropertyPut(String key, Supplier<String> value, Consumer<String> setter) {
			this.key = key;
			this.value = value;
			this.setter = setter;
		}
		PropertyPut(String key, String value, Consumer<String> setter) {
			this(key, () -> value, setter);
		}
		PropertyPut(String key, String value) {
			this(key, () -> value, s -> {});
		}
		public String getKey() {return key;}
		public Supplier<String> getValue() {return value;}
		public Consumer<String> getSetter() {return setter;}
	}
	
	private PropertiesFile createProperties(String resourcePath, Supplier<String> externalPath, List<PropertyPut> propertyPuts) {
		PropertiesFile propertiesFile = new PropertiesFile();
		
		UnicodeProperties properties = propertiesFile.getProperties();
		
		propertyPuts.forEach(put -> put.getSetter().accept(put.getValue().get()));
		
		try {
			propertiesFile.setResourcePath(resourcePath);
			propertiesFile.setExternalPath(externalPath.get());
			propertiesFile.setProperties(properties);
			
			if (propertiesFile.existExternal()) {
				propertiesFile.readExternal();
				propertyPuts.forEach(put -> {
					String value = properties.getProperty(put.getKey());
					System.out.println("value: " +value);
					properties.put(put.getKey(), value);
					
//					putProperty(properties, put.getKey(), value, put.getSetter());
				});
			} else {
				propertiesFile.createExternal();
				propertyPuts.forEach(put -> {
					properties.put(put.getKey(), put.getValue().get());
//					putProperty(properties, put.getKey(), put.getValue().get(), put.getSetter());
				});
				propertiesFile.writeExternal();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return propertiesFile;
	}
	
	
	public void init() {
		if (this.getBootApplicationClass() == null)
			throw new IllegalArgumentException("use setBootApplicationClass(BootApplication.class)");
//		
//		if (BootUtilsServer.isRunnableJarExecution(BootApplication.class)) {
//			File parentDir = BootUtilsServer.getRunnableJarExecutionDirectory(BootApplication.class);
//			this.setParentDirectory(parentDir.getPath());
//		} else {
//			this.setParentDirectory(System.getProperty("user.home") +"\\Desktop");
//		}
//		
//		List<PropertyPut> configPuts = new ArrayList<PropertyPut>();
//		configPuts.add(new PropertyPut("parent.directory.path", parentDirectory, this::setParentDirectory));
//		configPuts.add(new PropertyPut("home.path", () -> getParentDirectory() +"\\phantomData", this::setHomePath));
//		configPuts.add(new PropertyPut("database.path", () -> getHomePath() + "\\db", this::setDatabasePath));
//		configPuts.add(new PropertyPut("database.name.file", "h2", this::setDatabaseFilename));
//		configPuts.add(new PropertyPut("database.name.ext", "db", this::setDatabaseFileExtension));
//		configPuts.add(new PropertyPut("config-path", () -> getHomePath() +"\\config", this::setConfigPath));
//		configPuts.add(new PropertyPut("files.path", () -> getHomePath() +"\\data", this::setFilesPath));
//		configPuts.add(new PropertyPut("logs.path", () -> getHomePath() +"\\logs", this::setLogsPath));
//		PropertiesFile configFile = createProperties("config.properties", 
//				() -> getConfigPath() +"\\config.properties", 
//				configPuts);
//		
////		InputStream inputStream = null;
////		try {
////			inputStream = IOutil.readExternalFile("D:\\Users\\Marc\\Desktop\\application.properties");
////		} catch (FileNotFoundException e2) {
////			// TODO Auto-generated catch block
////			e2.printStackTrace();
////		}
//		
//		File file = new File("src\\main\\resources\\config.properties");
////		file.getParentFile().mkdirs();
////		try {
////			file.createNewFile();
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		OutputStream outputstream = null;
//		try {
//			outputstream = new FileOutputStream(file);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		UnicodeProperties configProps = IOutil.readProperties("config.properties");
//		configProps.clear();
//		configProps.putAll(configFile.getProperties());
//		
//		try {
//			configProps.store(outputstream, "");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println("////// config properties ///////////");
//		System.out.println(configProps.toString().replaceAll(", ", "\n"));
//		System.out.println();
//		
//		
////		
////		System.out.println(parentDir.getAbsolutePath());
////		System.out.println("jdbc:h2:" +parentDir.getAbsolutePath() +"/phantomData/db/phantoms;AUTO_SERVER=TRUE");
//		
////		CustomProperties applicationProperties = IOutil.readProperties("application.properties");
//		
//		
//		
//		
//		
////		InputStream inputStream = null;
////		try {
////			inputStream = IOutil.readExternalFile("D:\\Users\\Marc\\Desktop\\application.properties");
////		} catch (FileNotFoundException e2) {
////			// TODO Auto-generated catch block
////			e2.printStackTrace();
////		}
////		
////		File file = new File(inputStream);
////		
////		
////		
//		
//		List<PropertyPut> applicationPuts = new ArrayList<PropertyPut>();
//		applicationPuts.add(new PropertyPut("spring.datasource.hikari.connection-timeout", "20000"));
//		applicationPuts.add(new PropertyPut("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect"));
//		applicationPuts.add(new PropertyPut("spring.datasource.url", () -> 
//			"jdbc:h2:" +getDatabasePath() +"\\" +getDatabaseFilename() +";AUTO_SERVER=TRUE", this::setSpringDatasourceUrl));
//		applicationPuts.add(new PropertyPut("endpoints.shutdown.sensitive", "false"));
//		applicationPuts.add(new PropertyPut("spring.datasource.password", "1234", this::setDatabasePassword));
//		applicationPuts.add(new PropertyPut("spring.jpa.properties.hibernate.format_sql", "true"));
//		applicationPuts.add(new PropertyPut("management.endpoint.shutdown.enabled", "true"));
//		applicationPuts.add(new PropertyPut("spring.h2.console.enabled", "true"));
//		applicationPuts.add(new PropertyPut("management.endpoints.web.exposure.include", "*"));
//		applicationPuts.add(new PropertyPut("spring.datasource.username", "admin", this::setDatabaseUsername));
//		applicationPuts.add(new PropertyPut("spring.jpa.properties.hibernate.id.new_generator_mappings", "false"));
//		applicationPuts.add(new PropertyPut("endpoints.shutdown.enabled", "true"));
//		applicationPuts.add(new PropertyPut("sql.driver", "org.h2.Driver", this::setSqlDriver));
//		applicationPuts.add(new PropertyPut("spring.datasource.hikari.maximum-pool-size", "12"));
//		applicationPuts.add(new PropertyPut("server.port", () -> "8183", this::setUrlLocalhost));
//		applicationPuts.add(new PropertyPut("spring.jpa.hibernate.ddl-auto", "update"));
//		applicationPuts.add(new PropertyPut("spring.datasource.hikari.max-lifetime", "1200000"));
//		applicationPuts.add(new PropertyPut("spring.datasource.hikari.minimum-idle", "5"));
//		applicationPuts.add(new PropertyPut("spring.datasource.hikari.idle-timeout", "300000"));
//		PropertiesFile applicationFile = createProperties("application.properties", 
//				() -> getConfigPath() +"\\application.properties", 
//				applicationPuts);
//		
//		
//		
////		
////		
////		UnicodeProperties applicationProperties = new UnicodeProperties();
////		
////		
////		PropertiesFile applicationFile = new PropertiesFile();
////		applicationFile.setProperties(applicationProperties);
////		try {
////			// just .../Desktop for classpath 
////			applicationFile.setExternalPath("D:/Users/Marc/Desktop/application.properties");
////			applicationFile.setResourcePath("application.properties");
////			applicationFile.init();
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//		
//		
////		
//		
////		System.out.println("jdbc:h2:" +"D:/Users/Marc/Desktop/phantomData4w/db" +"/phantoms;AUTO_SERVER=TRUE");
////		applicationProperties.remove("spring.datasource.url");
////		
////		applicationProperties.put("spring.datasource.url", "jdbc:h2:" +"D:/Users/Marc/Desktop/phantomData4w/db" +"/phantoms;AUTO_SERVER=TRUE");
//		
//		
//		
////		this.setApplicationProperties(applicationProperties);
//		
////		System.out.println("application properties");
////		System.out.println(applicationProperties.toString().replaceAll(", ", "\n"));
//		
////		this.setSpringDatasourceUrl(applicationProperties.getProperty("spring.datasource.url"));
////		this.setDatabaseUsername(applicationProperties.getProperty("spring.datasource.username"));
////		this.setDatabasePassword(applicationProperties.getProperty("spring.datasource.password"));
////		
//		
//		
////		this.setUrlLocalhost("http://localhost:" +applicationProperties.getProperty("server.port"));
////		this.setUrlLocalhost("http://localhost:" +"8183");
//		
////		try {
////			IOutil.addExternalDirectoryToClassPath("src\\main\\resources\\application.properties");
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		File appfile = new File("src\\main\\resources\\application.properties");
//		appfile.getParentFile().mkdirs();
//		try {
//			appfile.createNewFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		OutputStream appOutputstream = null;
//		try {
//			appOutputstream = new FileOutputStream(appfile);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		UnicodeProperties appProps = IOutil.readProperties("application.properties");
//		appProps.clear();
//		appProps.putAll(applicationFile.getProperties());
//		
//		appProps.setProperty("spring.datasource.url","jdbc:h2:D:/Users/Marc/Desktop/phantomData/db/phantoms;AUTO_SERVER=TRUE");
//		
//		try {
//			appProps.store(appOutputstream, "");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println("////// application properties ///////////");
//		System.out.println(appProps.toString().replaceAll(", ", "\n"));
//		System.out.println();
		
//		this.setBootApplicationClass(BootApplication.class);
		this.setConfigPath("D:\\Users\\Marc\\Desktop\\PhantomData\\config");
		
		this.setDatabaseFileExtension("db");
		this.setDatabaseFilename("phantoms");
		this.setDatabasePassword("1234");
		this.setDatabasePath("D:\\Users\\Marc\\Destkop\\phantomData\\db");
		this.setDatabaseUsername("admin");
		this.setFilesPath("D:\\Users\\Marc\\Desktop\\phantomData\\data");
		this.setHomePath("D:\\Users\\Marc\\Destkop\\phantomData");
		this.setLogsPath("D:\\Users\\Marc\\Desktop\\phantomData\\logs");
		this.setParentDirectory("D:\\Users\\Marc\\Desktop");
		this.setSpringDatasourceUrl("jdbc:h2:D:/Users/Marc/Desktop/phantomData/db/phantoms;AUTO_SERVER=TRUE");
		this.setSqlDriver("org.h2.Driver");
		this.setUrlLocalhost("http://localhost:" +"8183");
		this.setUrlShutdownActuator("8183/actuator/shutdown");
		
		
		
		
		
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
	
	private void setConfigPath(String configpath) {
		this.configPath = configpath;
	}
	
	private void setSqlDriver(String sqlDriver) {
		this.sqlDriver = sqlDriver;
	}


	private void setSpringDatasourceUrl(String springDatasourceUrl) {
		this.springDatasourceUrl = springDatasourceUrl;
	}


	private void setLogsPath(String path) {
		this.logsPath = path;
		new File(path).mkdirs();
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
		return IOutil.readProperties("config.properties").getProperty("database.path");
	}
	
	public String getFilesPath() {
//		return filesPath;
		return IOutil.readProperties("application.properties").getProperty("files.path");
	}
	
	public String getLogsPath() {
//		return logsPath;
		return IOutil.readProperties("application.properties").getProperty("logs.path");
	}
	
	public String getParentDirectory() {
		return parentDirectory;
//		return IOutil.readProperties("config.properties").getProperty("parent.directory.path");
	}
	
	private String getHomePath() {
		return homePath;
	}
	
	public String getDatabaseFilename() {
//		return databaseFilename;
		return IOutil.readProperties("config.properties").getProperty("database.name.file");
	}
	
	public String getDatabaseFileExtension() {
//		return databaseFileExtension;
		return IOutil.readProperties("config.properties").getProperty("database.name.ext");
	}
	
	public String getUrlShutdownActuator() {
//		return urlShutdownActuator;
		return getUrlLocalhost() +"/actuator/shutdown";
		
//		return IOutil.readProperties("application.properties").getProperty("database.name.ext");
	}
	
	public String getSpringDatasourceUrl() {
//		return springDatasourceUrl;
		return IOutil.readProperties("application.properties").getProperty("spring.datasource.url");
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
	
	public Consumer<String[]> getConsumer() {
		return consumer;
	}

	public int getnStartupConsoleLines() {
		return nStartupConsoleLines;
	}
	
	
}
