package org.artorg.tools.phantomData.server.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class PropertiesFile extends RestorableFile {
	private UnicodeProperties properties;
	private PropertyPut[] propertyPuts;
	private boolean externalConfigOverride = false;
	
	public PropertiesFile(String externalPath, PropertyPut[] propertyPuts, boolean externalConfigOverride) {
		this();
		super.setExternalPath(externalPath);
		this.setPropertyPuts(propertyPuts);
		this.setExternalConfigOverride(externalConfigOverride);
		this.init();
	}
	
	public PropertiesFile() {
		properties = new UnicodeProperties();
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
	
	public void init() {
		try {
			if (existExternal()) {
				if (externalConfigOverride) {
					loadInitProperties();
					writeExternal();
				} else {
					readExternal();
				}
			} else {
				createExternal();
				loadInitProperties();
				writeExternal();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void loadInitProperties() {
		Arrays.stream(propertyPuts).forEach(put -> {
			properties.put(put.getKey(), put.getValue());
		});
	}
	
	public void writeExternal(String externalPath) throws IOException {
		OutputStream outputStream = this.createExternalOutputStream();
		properties.store(outputStream, "");
	}
	
	public UnicodeProperties getProperties() {
		return properties;
	}	
	
	public void setProperties(UnicodeProperties properties) {
		this.properties = properties;
	}
	
	public String getProperty(String key) {
		return this.properties.getProperty(key);
	}

	public PropertyPut[] getPropertyPuts() {
		return propertyPuts;
	}

	public void setPropertyPuts(PropertyPut[] propertyPuts) {
		this.propertyPuts = propertyPuts;
	}
	
	public boolean isExternalConfigOverride() {
		return externalConfigOverride;
	}

	public void setExternalConfigOverride(boolean externalConfigOverride) {
		this.externalConfigOverride = externalConfigOverride;
	}

}
