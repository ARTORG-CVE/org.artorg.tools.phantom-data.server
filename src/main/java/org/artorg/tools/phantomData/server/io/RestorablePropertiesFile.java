package org.artorg.tools.phantomData.server.io;

import java.io.IOException;
import java.util.Properties;

public class RestorablePropertiesFile extends RestorableCustomFile {
	private final Properties properties;

	{
		properties = new Properties();
	}
	
	public RestorablePropertiesFile(String resourcePath, String externalPath, Class<?> mainClass) {
		super(resourcePath, externalPath, mainClass);
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
		readResource();
	}
	
	public void init() throws IOException {
		if (existExternal()) {
			readExternal();
		} else {
			createExternal();
			writeExternal();
		}
	}
	
	public Properties getProperties() {
		return properties;
	}	

}
