package org.artorg.tools.phantomData.server.repositories.phantom;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.phantom.SimulationPhantom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "SIMULATION_PHANTOMS")
public interface SimulationPhantomRepository extends CrudRepository<SimulationPhantom, UUID>{

}
