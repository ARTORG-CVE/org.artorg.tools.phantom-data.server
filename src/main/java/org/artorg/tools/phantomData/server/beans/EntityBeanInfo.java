package org.artorg.tools.phantomData.server.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;

public class EntityBeanInfo {
	private static final EntityBeanInfo baseEntityBeanInfo;
	private final Class<?> entityClass;
	private final Set<PropertyDescriptor> allPropertyDescriptors;
	private final Set<PropertyDescriptor> notBasePropertyDescriptors;
	private final Function<Object, List<Object>> entityGetters;
	private final Function<Object, List<Object>> propertiesGetters;
	private final Function<Object, List<Collection<Object>>> entityCollectionGetters;
	private final Map<Class<?>, Map<String, Function<Object, Object>>> getterFunctionsMap;
	private final Map<Class<?>, Map<String, BiConsumer<Object, Object>>> setterFunctionsMap;

	static {
		baseEntityBeanInfo = new EntityBeanInfo(AbstractBaseEntity.class);
	}

	@SuppressWarnings("unchecked")
	public EntityBeanInfo(Class<?> entityClass) {
		if (!entityClass.isAnnotationPresent(Entity.class))
			if (entityClass != AbstractBaseEntity.class)
				throw new IllegalArgumentException();
		this.entityClass = entityClass;

		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(entityClass);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}

		allPropertyDescriptors = Arrays
			.asList(beanInfo.getPropertyDescriptors())
			.stream().collect(Collectors.toSet());

		if (entityClass == AbstractBaseEntity.class)
			notBasePropertyDescriptors = new HashSet<PropertyDescriptor>();
		else {
			notBasePropertyDescriptors = allPropertyDescriptors.stream()
				.filter(d -> {
					return !(baseEntityBeanInfo.allPropertyDescriptors.stream()
						.filter(bd -> bd == d).findFirst().isPresent());
				}).collect(Collectors.toSet());
		}

		entityGetters = bean -> notBasePropertyDescriptors.stream()
			.filter(d -> d.getPropertyType().isAnnotationPresent(Entity.class))
			.map(d -> getValue(d, bean))
			.filter(entity -> entity != null)
			.collect(Collectors.toList());

		entityCollectionGetters = bean -> notBasePropertyDescriptors.stream()
			.filter(d -> !d.getPropertyType().isAnnotationPresent(Entity.class))
			.filter(d -> Collection.class.isAssignableFrom(d.getPropertyType()))
			.filter(d -> !((Collection<Object>) getValue(d, bean)).isEmpty())
			.filter(d -> ((Collection<Object>) getValue(d, bean)).stream()
				.findFirst().get().getClass().isAnnotationPresent(Entity.class))
//				.filter(d -> d.getPropertyType().isAnnotationPresent(Entity.class))

			.map(d -> ((Collection<Object>) getValue(d, bean)))
			.filter(entity -> entity != null)
			.collect(Collectors.toList());

		propertiesGetters = bean -> notBasePropertyDescriptors.stream()
			.filter(d -> !d.getPropertyType().isAnnotationPresent(Entity.class))
			.filter(
				d -> !Collection.class.isAssignableFrom(d.getPropertyType()))
			.map(d -> getValue(d, bean))
			.collect(Collectors.toList());

		getterFunctionsMap = notBasePropertyDescriptors.stream()
			.collect(Collectors
				.groupingBy((PropertyDescriptor d) -> d.getPropertyType(),
					Collectors.toMap(d -> d.getName(), d -> {
						return bean -> getValue(d, bean);
					})));

		setterFunctionsMap = notBasePropertyDescriptors.stream()
			.collect(Collectors
				.groupingBy((PropertyDescriptor d) -> d.getPropertyType(),
					Collectors.toMap(d -> d.getName(), d -> {
						return (bean, value) -> d.createPropertyEditor(bean)
							.setValue(value);
					})));

	}

	public static Class<?> getBaseEntityClass() {
		return baseEntityBeanInfo.getEntityClass();
	}

	public List<Object> getBaseValues(Object bean) {
		return baseEntityBeanInfo.allPropertyDescriptors.stream()
			.map(d -> getValue(d, bean)).collect(Collectors.toList());
	}

	public List<Object> getBaseEntities(Object bean) {
		return baseEntityBeanInfo.getEntities(bean);
	}

	public List<Object> getBaseProperties(Object bean) {
		return baseEntityBeanInfo.getProperties(bean);
	}

	public List<Collection<Object>> getBaseEntityCollections(Object bean) {
		return baseEntityBeanInfo.getEntityCollections(bean);
	}

	private Object getValue(PropertyDescriptor descriptor, Object bean) {
		try {
			return descriptor.getReadMethod().invoke(bean);
		} catch (IllegalAccessException | IllegalArgumentException
			| InvocationTargetException e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException();
	}

	public List<Object> getEntities(Object bean) {
		if (bean == null)
			return new ArrayList<Object>();
		return entityGetters.apply(bean);
	}

	public List<Collection<Object>> getEntityCollections(Object bean) {
		if (bean == null)
			return new ArrayList<Collection<Object>>();
		return entityCollectionGetters.apply(bean);
	}

	public List<Object> getProperties(Object bean) {
		if (bean == null)
			return new ArrayList<Object>();
		return propertiesGetters.apply(bean);
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public Function<Object, Object> getGetterAsFunction(Class<?> type,
		String name) {
		return getterFunctionsMap.get(type).get(name);
	}

	public BiConsumer<Object, Object> getSetterAsFunction(Class<?> type,
		String name) {
		return setterFunctionsMap.get(type).get(name);
	}

}
