package org.artorg.tools.phantomData.server.repository;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.property.Properties;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "PROPERTIES")
public interface PropertiesRepository extends CrudRepository<Properties, UUID> {

}
