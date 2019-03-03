package org.artorg.tools.phantomData.server.boot;

import static org.toilelibre.libe.curl.Curl.curl;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.artorg.tools.phantomData.server.BootApplication;
import org.artorg.tools.phantomData.server.logging.Logger;
import org.artorg.tools.phantomData.server.models.base.DbFile;
import org.springframework.boot.SpringApplication;

import huma.io.ConsoleDiverter;

/**
 * Starts a Spring Boot server application.
 * <h3>Starting</h3> Use {@link #setServerStartedEmbedded(boolean)} for starting
 * the application in a single task. Call
 * {@link #initBeforeServerStart(Class, ConsoleFrame, StartupProgressFrame)} as
 * an initialization. Call {@link #startSpringServer(String[])} for starting the
 * server.
 * 
 * <h3>Shutdown</h3> Use {@link #shutdownSpringServer()} for shutting down the
 * server in correct way.
 */
public abstract class ServerBooter extends PropertiesBooter {
	private boolean serverStartedEmbedded = false;
	
	/**
	 * @param bootApplicationClass The Spring boot application class annotated with
	 *                             {@link org.springframework.boot.autoconfigure.SpringBootApplication}.
	 * @param consoleFrame         An implementation or {@code null}.
	 * @param startupProgressFrame An implementation or {@code null}.
	 */
	public void initBeforeServerStart(Class<?> bootApplicationClass,
			ConsoleFrame consoleFrame, StartupProgressFrame startupProgressFrame) {
		setBootApplicationClass(BootApplication.class);
		setExternalConfigOverridable(false);
		setConsoleDiverter(new ConsoleDiverter());
		setConsoleFrame(consoleFrame);
		setStartupFrame(startupProgressFrame);
		init();
		prepareFileStructure();
		DbFile.setFilesPath(getFilesPath());
	}
	
	/**
	 * A default procedure for managing the startupFrame and consoleFrame.
	 * 
	 * @see StartupProgressFrame
	 * @see ConsoleFrame
	 */
	public void finish() {
		getStartupFrame().setVisible(false);
		getStartupFrame().dispose();
		if (!getConsoleFrame().isErrorOccured() && !isErrorOccured()
				&& !isDebugConsoleMode())
			getConsoleFrame().setVisible(false);
		else getConsoleFrame().setVisible(true);
	}
	
	/** {@inheritDoc} */
	@Override
	public void handleException(Exception e) {
		e.printStackTrace();
		if (e instanceof org.springframework.boot.web.embedded.tomcat.ConnectorStartFailedException) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame,
					"Couldn't configured to listen on port " + getPort());
			frame.dispose();
		} else {
			getConsoleFrame().setTitle("Phantom Database - Exception thrown!");
			setErrorOccured(true);
			getConsoleFrame().setVisible(true);
		}
	}
	
	/**
	 * Deletes all files corresponding by the application based on the paths in the
	 * configuration files. All files in {@code phantomData/} will be deleted. The
	 * parent directory, where the runnable *.jar file is located will not deleted.
	 * 
	 * @see #prepareFileStructure()
	 */
	public void deleteFileStructure() {
		try {
			FileUtils.deleteDirectory(new File(getHomePath()));
			FileUtils.deleteDirectory(new File(getDatabasePath()));
			FileUtils.deleteDirectory(new File(getFilesPath()));
			FileUtils.deleteDirectory(new File(getLogsPath()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setInitialized(false);
	}
	
	/**
	 * Creates all important paths based on the paths in the configuration files.
	 */
	public void prepareFileStructure() {
		new File(getHomePath()).mkdirs();
		new File(getDatabasePath()).mkdirs();
		new File(getFilesPath()).mkdirs();
		new File(getLogsPath()).mkdirs();
	}
	
	/**
	 * Starts the Spring Boot server application in same thread an will return after
	 * a successful boot or throws an exception if connection could not built.
	 * 
	 * @param args Command line arguments. Can be empty.
	 * @throws IllegalArgumentException if connection could not built.
	 */
	public void startSpringServer(String[] args) {
		if (!this.isInitialized()) throw new RuntimeException("Not initialized!");
		if (!isConnected()) SpringApplication.run(getBootApplicationClass(), args);
		if (!isConnected()) throw new IllegalArgumentException(
				"server couldn't load configuration: url Localhost: "
						+ getUrlLocalhost());
		Logger.info.println("Server - Connected to database at " + getDatabasePath());
		Logger.info.println("Server - Connected to files at " + getFilesPath());
		Logger.info.println("Server - Started succesful on port " + getPort());
	}
	
	/**
	 * Shuts down the server application.
	 */
	public void shutdownSpringServer() {
		if (isConnected()) curl("-X POST " + getUrlShutdownActuator());
	}
	
	/**
	 * @return {@code true} if a connection could built with configured settings.
	 */
	public boolean isConnected() {
		try {
			URL siteURL = new URL(getUrlLocalhost());
			HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			
			int code = connection.getResponseCode();
			if (code == 200) { return true; }
		} catch (Exception e) {}
		return false;
	}
	
	/**
	 * @return Connection to this database based on loaded properties.
	 * @throws ClassNotFoundException If SQL driver was not found.
	 * @throws SQLException           If URL, username or password is wrong.
	 * @see #getSpringDatasourceUrl()
	 * @see #getDatabaseUsername()
	 * @see #getDatabasePassword()
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(getSqlDriver());
		return DriverManager.getConnection(getSpringDatasourceUrl(),
				getDatabaseUsername(), getDatabasePassword());
	}
	
	/**
	 * @return {@code true} if client will boot in same task. Can be used for
	 *         shutting down the whole application in embedded mode.
	 */
	public boolean isServerStartedEmbedded() {
		return serverStartedEmbedded;
	}
	
	/**
	 * @param serverStartedEmbedded {@code true} if client will boot in same task.
	 *                              Can be used for shutting down the whole
	 *                              application in embedded mode.s
	 */
	public void setServerStartedEmbedded(boolean serverStartedEmbedded) {
		this.serverStartedEmbedded = serverStartedEmbedded;
	}
	
}
