package org.artorg.tools.phantomData.server.repositories.phantom;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.phantom.Material;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "MATERIALS")
public interface MaterialRepository extends CrudRepository<Material, UUID> {

}
