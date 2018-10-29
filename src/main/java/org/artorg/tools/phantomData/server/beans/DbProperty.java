package org.artorg.tools.phantomData.server.beans;

import java.beans.PropertyDescriptor;

public class DbProperty {
	private final PropertyDescriptor descriptor;
	private final Object bean;
	private final Object value;
	private final String name;
	private final boolean isEntity;
	
	private final boolean isCollection;

	public DbProperty(PropertyDescriptor descriptor, Object bean, boolean isEntity, boolean isCollection) {
		this.descriptor = descriptor;
		this.bean = bean;
		this.value = EntityBeanInfo.getValue(descriptor, bean);
		this.name = descriptor.getName();
		this.isEntity = isEntity;
		this.isCollection = isCollection;
	}
	
	
	public PropertyDescriptor getDescriptor() {
		return descriptor;
	}

	public Object getBean() {
		return bean;
	}

	public Object getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	public boolean isEntity() {
		return isEntity;
	}

	public boolean isCollection() {
		return isCollection;
	}


}