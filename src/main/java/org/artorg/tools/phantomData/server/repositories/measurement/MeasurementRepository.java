package org.artorg.tools.phantomData.server.repositories.measurement;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.measurement.Measurement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "MEASUREMENTS")
public interface MeasurementRepository extends CrudRepository<Measurement, UUID> {

}
