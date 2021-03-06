package org.artorg.tools.phantomData.server.repositories.base.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.models.base.property.PropertyField;
import org.artorg.tools.phantomData.server.models.base.property.StringProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "STRING_PROPERTY_FIELDS")
public interface StringPropertyRepository extends CrudRepository<StringProperty, UUID> {
	
	List<StringProperty> findByPropertyField(PropertyField propertyField);
	
}
