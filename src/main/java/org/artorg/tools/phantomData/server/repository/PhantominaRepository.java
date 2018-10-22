package org.artorg.tools.phantomData.server.repository;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.Phantomina;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "PHANTOMINAS")
public interface PhantominaRepository extends  CrudRepository<Phantomina, UUID>{

}
