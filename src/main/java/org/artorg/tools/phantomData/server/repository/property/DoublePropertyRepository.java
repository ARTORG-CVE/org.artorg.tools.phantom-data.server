package org.artorg.tools.phantomData.server.repository.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.base.property.DoubleProperty;
import org.artorg.tools.phantomData.server.model.base.property.PropertyField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "DOUBLE_PROPERTY_FIELDS")
public interface DoublePropertyRepository extends CrudRepository<DoubleProperty, UUID> {

	List<DoubleProperty> findByPropertyField(PropertyField propertyField);
	
}