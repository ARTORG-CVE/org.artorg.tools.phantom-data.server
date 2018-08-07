package org.artorg.tools.phantomData.server.repository.property;

import java.util.List;

import org.artorg.tools.phantomData.server.model.property.BooleanProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "BOOLEAN_PROPERTY_FIELDS")
public interface BooleanPropertyRepository extends CrudRepository<BooleanProperty, Integer> {

	List<BooleanProperty> findByPropertyField(PropertyField propertyField);
	
}
