package org.artorg.tools.phantomData.server.repository.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.model.property.StringProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "STRING_PROPERTY_FIELDS")
public interface StringPropertyRepository extends CrudRepository<StringProperty, UUID> {
	
	List<StringProperty> findByPropertyField(PropertyField propertyField);
	
}
