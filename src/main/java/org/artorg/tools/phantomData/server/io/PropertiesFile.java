package org.artorg.tools.phantomData.server.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.artorg.tools.phantomData.server.boot.BootUtilsServer;

public class PropertiesFile extends RestorableFile {
	private UnicodeProperties properties;
	private boolean existExternalPath;

	{
		properties = new UnicodeProperties();
	}
	
	public PropertiesFile(String resourcePath, String externalPath) throws Exception {
		this();
		super.setResourcePath(resourcePath);
		super.setExternalPath(externalPath);
	}
	
	public PropertiesFile() {
		super.addReadConsumer(inputStream -> {
			try {
				properties.clear();
				properties.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		super.addWriteConsumer(outputStream -> {
			try {
				properties.store(outputStream, "");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	public void writeExternal(String externalPath) throws IOException {
		OutputStream outputStream = this.createExternalOutputStream();
		properties.store(outputStream, "");
	}
	
//	public void init() throws Exception {
//		if (existExternal()) {
//			existExternalPath = true;
//			readExternal();
//		} else {
//			createExternal();
//			readResource();
//			writeExternal();
//		}
//	}
	
	public UnicodeProperties getProperties() {
		return properties;
	}	
	
	public void setProperties(UnicodeProperties properties) {
		this.properties = properties;
	}
	
	public String getResourceProperty(String key) {
		return this.properties.getProperty(key);
		
//		String value = null;
//		InputStream inputStream = null;
//		try {
//			UnicodeProperties properties = new UnicodeProperties();
//			String filename = getResourcePath();
//			File file = new File(filename);
//			if (BootUtilsServer.isRunnableJarExecution(getClass())) {
//				filename =  file.getName();
//				file = new File(filename);
//			}
//			
//			inputStream = new FileInputStream(file);
//			properties.load(inputStream);
//			value = properties.getProperty(key);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			inputStream.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		if (value == null) throw new NullPointerException("key: " +key +", resourcePath: " +getResourcePath());
//		return value;
	}

}
