package org.artorg.tools.phantomData.server.repository.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.base.property.PropertyField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "PROPERTY_FIELDS")
public interface PropertyFieldRepository extends CrudRepository<PropertyField, UUID> {

	List<PropertyField> findByName(String name);
}
