package org.artorg.tools.phantomData.server.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Collectors;

import org.artorg.tools.phantomData.server.boot.UnicodeProperties;

public class PropertiesFile extends RestorableFile {
	private UnicodeProperties properties;

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
	
	public void init() throws Exception {
		if (existExternal()) {
			readExternal();
		} else {
			createExternal();
			readResource();
			writeExternal();
		}
	}
	
	public UnicodeProperties getProperties() {
		return properties;
	}	
	
	public void setProperties(UnicodeProperties properties) {
		this.properties.clear();
		this.properties.putAll(properties.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue())));
	}

}
