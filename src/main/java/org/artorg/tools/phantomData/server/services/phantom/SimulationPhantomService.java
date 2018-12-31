package org.artorg.tools.phantomData.server.services.phantom;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.phantom.SimulationPhantom;
import org.artorg.tools.phantomData.server.repositories.phantom.SimulationPhantomRepository;
import org.artorg.tools.phantomData.server.service.IServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class SimulationPhantomService implements IServiceDefault<SimulationPhantom> {

	@Autowired
	private SimulationPhantomRepository repository;
	
	@Override
	public CrudRepository<SimulationPhantom, UUID> getRepository() {
		return repository;
	}

}