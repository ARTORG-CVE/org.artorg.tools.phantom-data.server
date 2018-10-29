package org.artorg.tools.phantomData.server.beans;

public class DbNode {
	private final Object value;
	private final String name;

	public DbNode(Object value, String name) {
		this.name = name;
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

}