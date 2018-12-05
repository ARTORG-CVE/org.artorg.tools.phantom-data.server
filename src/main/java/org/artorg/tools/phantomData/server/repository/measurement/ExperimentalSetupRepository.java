package org.artorg.tools.phantomData.server.repository.measurement;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.measurement.ExperimentalSetup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "EXPERIMENTAL_SETUPS")
public interface ExperimentalSetupRepository extends CrudRepository<ExperimentalSetup, UUID> {

}
