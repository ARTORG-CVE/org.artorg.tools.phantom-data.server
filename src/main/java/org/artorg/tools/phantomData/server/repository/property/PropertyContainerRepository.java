package org.artorg.tools.phantomData.server.repository.property;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.property.PropertyContainer;
import org.springframework.data.repository.CrudRepository;

public interface PropertyContainerRepository extends CrudRepository<PropertyContainer, UUID> {

}
