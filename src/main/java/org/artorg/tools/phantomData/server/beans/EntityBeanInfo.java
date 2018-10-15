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
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.Entity;

public class EntityBeanInfo {
	private final Class<?> entityClass;
	private final Set<PropertyDescriptor> propertyDescriptors;
	private final Function<Object, List<Object>> entityGetters;
	private final Function<Object, List<Object>> propertiesGetters;
	private final Function<Object, List<Collection<Object>>> entityCollectionGetters;
	private final Map<Class<?>, Map<String, Function<Object, Object>>> getterFunctionsMap;
	private final Map<Class<?>, Map<String, BiConsumer<Object, Object>>> setterFunctionsMap;

	public EntityBeanInfo(Class<?> entityClass) {
		if (!entityClass.isAnnotationPresent(Entity.class))
			throw new IllegalArgumentException();
		this.entityClass = entityClass;

		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(entityClass);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}

		propertyDescriptors = Arrays.asList(beanInfo.getPropertyDescriptors()).stream().collect(Collectors.toSet());

		entityGetters = bean -> propertyDescriptors.stream()
				.filter(d -> d.getPropertyType().isAnnotationPresent(Entity.class))
				.map(d -> getValue(d, bean))
				.filter(entity -> entity != null)
				.collect(Collectors.toList());
		
		entityCollectionGetters = bean -> propertyDescriptors.stream()
				.filter(d -> Collection.class.isAssignableFrom(d.getPropertyType())
//				.flatMap(d ->)
				.filter(d -> d.getPropertyType().isAnnotationPresent(Entity.class))
				.map(d -> getValue(d, bean))
				.filter(entity -> entity != null)
				.collect(Collectors.toList());
		
		propertiesGetters = bean -> propertyDescriptors.stream()
				.filter(d -> !d.getPropertyType().isAnnotationPresent(Entity.class))
				.map(d -> getValue(d, bean))
				.collect(Collectors.toList());

		getterFunctionsMap = propertyDescriptors.stream().collect(Collectors
				.groupingBy((PropertyDescriptor d) -> d.getPropertyType(), Collectors.toMap(d -> d.getName(), d -> {
					return bean -> getValue(d, bean);
				})));
		
		setterFunctionsMap = propertyDescriptors.stream().collect(Collectors
				.groupingBy((PropertyDescriptor d) -> d.getPropertyType(), Collectors.toMap(d -> d.getName(), d -> {
					return (bean,value) -> d.createPropertyEditor(bean).setValue(value);
				})));
		
		
	}
	
	private Object getValue(PropertyDescriptor descriptor, Object bean) {
		try {
			return descriptor.getReadMethod().invoke(bean);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException();
	}

	public List<Object> getEntities(Object bean) {
		if (bean == null) return new ArrayList<Object>();
		return entityGetters.apply(bean);
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

//	private final Set<Class<?>> entityClasses;
//	private final Map<Class<?>, Set<PropertyDescriptor>> introspectorMap;
//	private final Map<Class<?>, Function<Object, List<Object>>> entityGetterMap;
//	private Map<Class<?>, Map<Class<?>, Map<String, Function<Object, Object>>>> getterFunctionsMap;
//
//	{
//		introspectorMap = new HashMap<Class<?>, Set<PropertyDescriptor>>();
//		entityGetterMap = new HashMap<Class<?>, Function<Object, List<Object>>>();
//	}
//
//	public EntityBeanInfo(Reflections reflections) {
//		entityClasses = reflections.getSubTypesOf(DbPersistent.class).stream()
//				.filter(c -> c.isAnnotationPresent(Entity.class)).peek(c -> {
//					if (!introspectorMap.containsKey(c)) {
//						BeanInfo beanInfo = null;
//						try {
//							beanInfo = Introspector.getBeanInfo(c);
//						} catch (IntrospectionException e) {
//							e.printStackTrace();
//						}
//
//						Set<PropertyDescriptor> introspectors = Arrays.asList(beanInfo.getPropertyDescriptors())
//								.stream().collect(Collectors.toSet());
//						introspectorMap.put(c, introspectors);
//					}
//				}).collect(Collectors.toSet());
//
//		introspectorMap.entrySet().stream().forEach(e -> {
//			Function<Object, List<Object>> beanGetters = bean -> e.getValue().stream()
//					.map(d -> d.createPropertyEditor(bean).getValue()).collect(Collectors.toList());
//			entityGetterMap.put(e.getKey(), beanGetters);
//		});
//
//		introspectorMap.entrySet().stream().forEach(e -> {
//			Map<Class<?>, Map<String, Function<Object, Object>>> map = e.getValue().stream().collect(Collectors
//					.groupingBy((PropertyDescriptor d) -> d.getPropertyType(), Collectors.toMap(d -> d.getName(), d -> {
//						return bean -> d.createPropertyEditor(bean).getValue();
//					})));
//			getterFunctionsMap.put(e.getKey(), map);
//		});
//
//	}
//
//	public List<Object> getEntities(Class<?> entityClass, Object bean) {
//		return entityGetterMap.get(entityClass).apply(bean);
//	}
//
//	public Set<Class<?>> getEntityClasses() {
//		return entityClasses;
//	}
//	
//	public Function<Object,Object> getGetterAsFunction(Class<?> entityClass, Class<?> type, String name) {
//		return getterFunctionsMap.get(entityClass).get(type).get(name);
//	}

}
