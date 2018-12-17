package org.artorg.tools.phantomData.server.repositories.phantom;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.phantom.Phantomina;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "PHANTOMINAS")
public interface PhantominaRepository extends CrudRepository<Phantomina, UUID> {

}
