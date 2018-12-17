package org.artorg.tools.phantomData.server.repositories.base.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.models.base.property.DateProperty;
import org.artorg.tools.phantomData.server.models.base.property.PropertyField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "DATE_PROPERTY_FIELDS")
public interface DatePropertyRepository extends CrudRepository<DateProperty, UUID> {

	List<DateProperty> findByPropertyField(PropertyField propertyField);
	
}
