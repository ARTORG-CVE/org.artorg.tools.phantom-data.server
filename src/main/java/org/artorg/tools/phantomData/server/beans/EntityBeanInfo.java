package org.artorg.tools.phantomData.server.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;

public class EntityBeanInfo {
	private static final EntityBeanInfo baseEntityBeanInfo;
	private final Class<?> entityClass;
	private final List<PropertyDescriptor> allPropertyDescriptors;
	private final List<PropertyDescriptor> notBasePropertyDescriptors;
	private final Function<Object, List<PropertyDescriptor>> entityDescriptors;
	private final Function<Object, List<PropertyDescriptor>> collectionDescriptors;
	private final Function<Object, List<PropertyDescriptor>> propertiesDescriptors;

	private final Function<Object, List<Object>> entityGetters;
	private final Function<Object, List<Object>> propertiesGetters;
	private final Function<Object, List<Collection<Object>>> entityCollectionGetters;
	private final Map<Class<?>, Map<String, Function<Object, Object>>> getterFunctionsMap;
	private final Map<Class<?>,
		Map<String, BiConsumer<Object, Object>>> setterFunctionsMap;

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

		allPropertyDescriptors = Arrays.asList(beanInfo.getPropertyDescriptors()).stream()
			.collect(Collectors.toList());

		if (entityClass == AbstractBaseEntity.class)
			notBasePropertyDescriptors = new ArrayList<PropertyDescriptor>();
		else {
			notBasePropertyDescriptors = allPropertyDescriptors.stream().filter(d -> {
				return !(baseEntityBeanInfo.allPropertyDescriptors.stream()
					.filter(bd -> bd == d).findFirst().isPresent());
			}).collect(Collectors.toList());
		}

		entityDescriptors = bean -> notBasePropertyDescriptors.stream()
			.filter(d -> d.getPropertyType().isAnnotationPresent(Entity.class))
			.filter(d -> getValue(d, bean) != null).collect(Collectors.toList());

		entityGetters = bean -> entityDescriptors.apply(bean).stream()
			.map(d -> getValue(d, bean)).collect(Collectors.toList());

		collectionDescriptors = bean -> notBasePropertyDescriptors.stream()
			.filter(d -> !d.getPropertyType().isAnnotationPresent(Entity.class))
			.filter(d -> Collection.class.isAssignableFrom(d.getPropertyType()))
			.filter(d -> !((Collection<Object>) getValue(d, bean)).isEmpty())
			.filter(d -> ((Collection<Object>) getValue(d, bean)).stream().findFirst()
				.get().getClass().isAnnotationPresent(Entity.class))
			.filter(d -> getValue(d, bean) != null).collect(Collectors.toList());

		entityCollectionGetters = bean -> collectionDescriptors.apply(bean).stream()
			.map(d -> ((Collection<Object>) getValue(d, bean)))
			.collect(Collectors.toList());
		
		
		propertiesDescriptors = bean -> notBasePropertyDescriptors.stream()
			.filter(d -> !d.getPropertyType().isAnnotationPresent(Entity.class))
			.filter(d -> !Collection.class.isAssignableFrom(d.getPropertyType()))
			.filter(d -> getValue(d, bean) != null).collect(Collectors.toList());
	
		propertiesGetters = bean -> propertiesDescriptors.apply(bean).stream()
			.map(d -> getValue(d, bean))
			.collect(Collectors.toList());
		

//		propertiesGetters = bean -> notBasePropertyDescriptors.stream()
//			.filter(d -> !d.getPropertyType().isAnnotationPresent(Entity.class))
//			.filter(d -> !Collection.class.isAssignableFrom(d.getPropertyType()))
//			.map(d -> getValue(d, bean)).collect(Collectors.toList());

		getterFunctionsMap = notBasePropertyDescriptors.stream()
			.collect(Collectors.groupingBy((PropertyDescriptor d) -> d.getPropertyType(),
				Collectors.toMap(d -> d.getName(), d -> {
					return bean -> getValue(d, bean);
				})));

		setterFunctionsMap = notBasePropertyDescriptors.stream()
			.collect(Collectors.groupingBy((PropertyDescriptor d) -> d.getPropertyType(),
				Collectors.toMap(d -> d.getName(), d -> {
					return (bean, value) -> d.createPropertyEditor(bean).setValue(value);
				})));

	}

	public List<DbProperty> getNamedEntityValues(Object bean) {
		return entityDescriptors.apply(bean).stream().map(d -> new DbProperty(d, bean, true, false))
			.collect(Collectors.toList());
	}
	
	public List<DbProperty> getNamedCollectionValues(Object bean) {
		return collectionDescriptors.apply(bean).stream().map(d -> new DbProperty(d, bean, false, true))
			.collect(Collectors.toList());
	}

	public List<DbProperty> getNamedPropertiesValues(Object bean) {
		return propertiesDescriptors.apply(bean).stream().map(d -> new DbProperty(d, bean, false, false))
			.collect(Collectors.toList());
	}
	

	public static List<Object> getBaseValues(Object bean) {
		return baseEntityBeanInfo.allPropertyDescriptors.stream()
			.map(d -> getValue(d, bean)).collect(Collectors.toList());
	}

	public static List<Object> getBaseEntities(Object bean) {
		return baseEntityBeanInfo.getEntities(bean);
	}

	public static List<Object> getBaseProperties(Object bean) {
		return baseEntityBeanInfo.getProperties(bean);
	}

	public static List<Collection<Object>> getBaseEntityCollections(Object bean) {
		return baseEntityBeanInfo.getEntityCollections(bean);
	}

	public static Class<?> getBaseEntityClass() {
		return baseEntityBeanInfo.getEntityClass();
	}
	
	public static Object getValue(PropertyDescriptor descriptor, Object bean) {
		try {
			return descriptor.getReadMethod().invoke(bean);
		} catch (IllegalAccessException | IllegalArgumentException
			| InvocationTargetException e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException();
	}

	public List<Object> getEntities(Object bean) {
		if (bean == null) return new ArrayList<Object>();
		return entityGetters.apply(bean);
	}

	public List<Collection<Object>> getEntityCollections(Object bean) {
		if (bean == null) return new ArrayList<Collection<Object>>();
		return entityCollectionGetters.apply(bean);
	}

	public List<Object> getProperties(Object bean) {
		if (bean == null) return new ArrayList<Object>();
		return propertiesGetters.apply(bean);
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public Function<Object, Object> getGetterAsFunction(Class<?> type, String name) {
		return getterFunctionsMap.get(type).get(name);
	}

	public BiConsumer<Object, Object> getSetterAsFunction(Class<?> type, String name) {
		return setterFunctionsMap.get(type).get(name);
	}

	// Getters & Setters
	public List<PropertyDescriptor> getAllPropertyDescriptors() {
		return allPropertyDescriptors;
	}

	public List<PropertyDescriptor> getNotBasePropertyDescriptors() {
		return notBasePropertyDescriptors;
	}

}
