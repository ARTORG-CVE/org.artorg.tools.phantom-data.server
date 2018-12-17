package org.artorg.tools.phantomData.server.services.measurement;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.measurement.Measurement;
import org.artorg.tools.phantomData.server.repositories.measurement.MeasurementRepository;
import org.artorg.tools.phantomData.server.service.IServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService implements IServiceDefault<Measurement> {

	@Autowired
	private MeasurementRepository repository;
	
	@Override
	public CrudRepository<Measurement, UUID> getRepository() {
		return repository;
	}

}