package org.artorg.tools.phantomData.server.repository.measurement;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.measurement.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "UNITS")
public interface UnitRepository extends CrudRepository<Unit, UUID> {

}
