package org.artorg.tools.phantomData.server.io;

public class PropertyPut {
	private final String key;
	private final String value;
	
	public PropertyPut(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {return key;}
	
	public String getValue() {return value;}
	
}
