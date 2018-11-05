package org.artorg.tools.phantomData.server.repository.measurement;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.measurement.UnitPrefix;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "UNIT_PREFIXES")
public interface UnitPrefixRepository extends CrudRepository<UnitPrefix, UUID> {

}
