package org.artorg.tools.phantomData.server.repository.measurement;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.measurement.PhysicalQuantity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "PHYSICAL_QUANTITY")
public interface PhysicalQuantityRepository extends CrudRepository<PhysicalQuantity, UUID> {

}
