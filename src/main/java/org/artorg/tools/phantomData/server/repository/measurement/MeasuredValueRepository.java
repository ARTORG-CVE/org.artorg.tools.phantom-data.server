package org.artorg.tools.phantomData.server.repository.measurement;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.measurement.MeasuredValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "MEASURED_VALUES")
public interface MeasuredValueRepository extends CrudRepository<MeasuredValue, UUID> {

}
