package org.artorg.tools.phantomData.server.repository.property;

import java.util.List;

import org.artorg.tools.phantomData.server.model.property.DateProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "DATE_PROPERTY_FIELDS")
public interface DatePropertyRepository extends CrudRepository<DateProperty, Integer> {

	List<DateProperty> findByPropertyField(PropertyField propertyField);
	
}
