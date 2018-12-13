package org.artorg.tools.phantomData.server.repository.base.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.base.property.DateProperty;
import org.artorg.tools.phantomData.server.model.base.property.PropertyField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "DATE_PROPERTY_FIELDS")
public interface DatePropertyRepository extends CrudRepository<DateProperty, UUID> {

	List<DateProperty> findByPropertyField(PropertyField propertyField);
	
}