package org.artorg.tools.phantomData.server.boot;

import javax.persistence.Entity;

import org.artorg.tools.phantomData.server.BootApplication;
import org.artorg.tools.phantomData.server.specification.DbPersistent;

public abstract class BeanEntityBooter extends PropertiesBooter {
	
	public void init() {
		super.init();
		createBeanInfos();
	}
	
	public void createBeanInfos() {
		BootApplication.getReflections().getSubTypesOf(DbPersistent.class).stream()
			.filter(c -> c.isAnnotationPresent(Entity.class))
			.forEach(c -> BootApplication.getBeanmap().addBeanClass(c));
	}
	

}
