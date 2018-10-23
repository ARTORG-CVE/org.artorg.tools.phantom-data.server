package org.artorg.tools.phantomData.server;

import org.artorg.tools.phantomData.server.beans.BeanMap;
import org.artorg.tools.phantomData.server.beans.EntityBeanInfos;
import org.reflections.Reflections;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {
	private static final Reflections reflections = new Reflections("org.artorg.tools.phantomData");
	private static final BeanMap beanMap = new BeanMap();
	private static final EntityBeanInfos entityBeanInfos = new EntityBeanInfos(reflections);

	public static void main(String[] args) {
		new DesktopSwingBootServer().boot(args);
	}
	
	public static Reflections getReflections() {
		return reflections;
	}
	
	public static BeanMap getBeanmap() {
		return beanMap;
	}

	public static EntityBeanInfos getEntitybeaninfos() {
		return entityBeanInfos;
	}
	
	

}
