package org.artorg.tools.phantomData.server.repositories.base.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.models.base.property.BooleanProperty;
import org.artorg.tools.phantomData.server.models.base.property.PropertyField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "BOOLEAN_PROPERTY_FIELDS")
public interface BooleanPropertyRepository extends CrudRepository<BooleanProperty, UUID> {

	List<BooleanProperty> findByPropertyField(PropertyField propertyField);
	
}
