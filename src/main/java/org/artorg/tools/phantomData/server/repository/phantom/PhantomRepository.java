package org.artorg.tools.phantomData.server.repository.phantom;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.phantom.Phantom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "PHANTOMS")
public interface PhantomRepository extends  CrudRepository<Phantom, UUID>{

}
