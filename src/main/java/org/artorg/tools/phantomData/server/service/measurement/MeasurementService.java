package org.artorg.tools.phantomData.server.service.measurement;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.measurement.Measurement;
import org.artorg.tools.phantomData.server.repository.measurement.MeasurementRepository;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService implements IService<Measurement> {

	@Autowired
	private MeasurementRepository repository;
	
	@Override
	public CrudRepository<Measurement, UUID> getRepository() {
		return repository;
	}

}