package org.artorg.tools.phantomData.server.repository.property;

import java.util.List;

import org.artorg.tools.phantomData.server.model.property.DoubleProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "DOUBLE_PROPERTY_FIELDS")
public interface DoublePropertyRepository extends CrudRepository<DoubleProperty, Integer> {

	List<DoubleProperty> findByPropertyField(PropertyField propertyField);
	
}