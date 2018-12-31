package org.artorg.tools.phantomData.server.services.measurement;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.measurement.Simulation;
import org.artorg.tools.phantomData.server.repositories.measurement.SimulationRepository;
import org.artorg.tools.phantomData.server.service.IServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class SimulationService implements IServiceDefault<Simulation> {

	@Autowired
	private SimulationRepository repository;
	
	@Override
	public CrudRepository<Simulation, UUID> getRepository() {
		return repository;
	}

}