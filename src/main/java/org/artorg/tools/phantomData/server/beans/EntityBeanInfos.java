package org.artorg.tools.phantomData.server.beans;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.reflections.Reflections;

public class EntityBeanInfos {
	private final Set<Class<?>> entityClasses;
	private final Map<Class<?>, EntityBeanInfo> beanInfoMap;
	
	public EntityBeanInfos(Reflections reflections) {
		entityClasses = reflections.getSubTypesOf(DbPersistent.class).stream()
				.filter(c -> c.isAnnotationPresent(Entity.class))
				.collect(Collectors.toSet());
		
		beanInfoMap = entityClasses.stream().collect(Collectors.toMap(c -> c, c -> new EntityBeanInfo(c)));
	}
	
	public List<Object> getEntities(Class<?> entityClass, Object bean) {
		return beanInfoMap.get(entityClass).getEntities(bean);
	}
	
	public Function<Object, Object> getGetterAsFunction(Class<?> entityClass, Class<?> type, String name) {
		return beanInfoMap.get(entityClass).getGetterAsFunction(type, name);
	}
	
	public BiConsumer<Object, Object> getSetterAsFunction(Class<?> entityClass, Class<?> type, String name) {
		return beanInfoMap.get(entityClass).getSetterAsFunction(type, name);
	}

}
