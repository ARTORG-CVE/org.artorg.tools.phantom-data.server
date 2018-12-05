package org.artorg.tools.phantomData.server.service.measurement;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.measurement.ExperimentalSetup;
import org.artorg.tools.phantomData.server.repository.measurement.ExperimentalSetupRepository;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ExperimentalSetupService implements IServiceDefault<ExperimentalSetup> {

	@Autowired
	private ExperimentalSetupRepository repository;
	
	@Override
	public CrudRepository<ExperimentalSetup, UUID> getRepository() {
		return repository;
	}
	
}
