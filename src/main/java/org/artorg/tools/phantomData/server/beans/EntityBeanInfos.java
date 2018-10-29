package org.artorg.tools.phantomData.server.beans;

import java.util.ArrayList;
import java.util.Collection;
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

		beanInfoMap = entityClasses.stream()
				.collect(Collectors.toMap(c -> c, c -> new EntityBeanInfo(c)));
	}
	
	
	
	
	public EntityBeanInfo getEntityBeanInfo(Class<?> entityClass) {
		return beanInfoMap.get(entityClass);
	}

	public List<Object> getEntities(Object bean) {
		if (bean == null)
			return new ArrayList<Object>();
		if (beanInfoMap.containsKey(bean.getClass()))
			return getEntities(bean.getClass(), bean);
		throw new IllegalArgumentException();
	}

	public List<Collection<Object>> getEntityCollections(Object bean) {
		if (bean == null)
			return new ArrayList<Collection<Object>>();
		if (beanInfoMap.containsKey(bean.getClass()))
			return getEntityCollections(bean.getClass(), bean);
		throw new IllegalArgumentException();
	}

	public List<Collection<Object>> getEntityCollections(Class<?> entityClass,
			Object bean) {
		return beanInfoMap.get(entityClass).getEntityCollections(bean);
	}

	public List<Object> getEntities(Class<?> entityClass, Object bean) {
		return beanInfoMap.get(entityClass).getEntities(bean);
	}

	public List<Object> getProperties(Object bean) {
		if (bean == null)
			return new ArrayList<Object>();
		if (beanInfoMap.containsKey(bean.getClass()))
			return getProperties(bean.getClass(), bean);
		throw new IllegalArgumentException();
	}

	public List<Object> getProperties(Class<?> entityClass, Object bean) {
		if (bean == null)
			return new ArrayList<Object>();
		return beanInfoMap.get(entityClass).getProperties(bean);
	}

	public Function<Object, Object> getGetterAsFunction(Class<?> entityClass,
			Class<?> type, String name) {
		return beanInfoMap.get(entityClass).getGetterAsFunction(type, name);
	}

	public BiConsumer<Object, Object> getSetterAsFunction(Class<?> entityClass,
			Class<?> type, String name) {
		return beanInfoMap.get(entityClass).getSetterAsFunction(type, name);
	}

	public Set<Class<?>> getEntityClasses() {
		return entityClasses;
	}

}
