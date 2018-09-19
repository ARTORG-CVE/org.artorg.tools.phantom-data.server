package org.artorg.tools.phantomData.server.repository.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.property.IntegerProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "INTEGER_PROPERTY_FIELDS")
public interface IntegerPropertyRepository extends CrudRepository<IntegerProperty, UUID> {

	List<IntegerProperty> findByPropertyField(PropertyField propertyField);
	
}
