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
import org.springframework.boot.SpringApplication;

public abstract class ServerBooter extends BeanEntityBooter {
	private boolean serverStartedEmbedded;

	{
		serverStartedEmbedded = false;
	}
	
	public boolean handleException(Exception e) {
		if (e instanceof org.springframework.boot.web.embedded.tomcat.ConnectorStartFailedException) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Couldn't configured to listen on port " +getPort());
			frame.dispose();
			return true;
		} 
		return false;
	}
	
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
	
	public void prepareFileStructure() {
		new File(getHomePath()).mkdirs();
		new File(getDatabasePath()).mkdirs();
		new File(getFilesPath()).mkdirs();
		new File(getLogsPath()).mkdirs();
	}
	
	public void startSpringServer(String[] args) {
		if (!this.isInitialized()) 
			throw new RuntimeException("Not initialized!");
		if (!isConnected()) 
			SpringApplication.run(getBootApplicationClass(), args);
		if (!isConnected()) 
			throw new IllegalArgumentException("server couldn't load configuration: url Localhost: " +getUrlLocalhost());
	}
	
	public void shutdownSpringServer() {
		if (isConnected()) {
			curl("-X POST " +getUrlShutdownActuator());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isConnected() {
        try {
            URL siteURL = new URL(getUrlLocalhost());
            HttpURLConnection connection = (HttpURLConnection) siteURL
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
 
            int code = connection.getResponseCode();
            if (code == 200) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
	
	public Connection getConnection() {
		try {
			Class.forName (getSqlDriver());
			return DriverManager.getConnection (
					getSpringDatasourceUrl(), 
					getDatabaseUsername(),
					getDatabasePassword()); 
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		throw new RuntimeException();
	}
	
	
	public boolean isServerStartedEmbedded() {
		return serverStartedEmbedded;
	}

	public void setServerStartedEmbedded(boolean serverStartedEmbedded) {
		this.serverStartedEmbedded = serverStartedEmbedded;
	}

}
