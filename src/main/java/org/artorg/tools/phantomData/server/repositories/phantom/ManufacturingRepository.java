package org.artorg.tools.phantomData.server.repositories.phantom;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.phantom.Manufacturing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "MANUFACTURINGS")
public interface ManufacturingRepository extends  CrudRepository<Manufacturing, UUID>{

}
