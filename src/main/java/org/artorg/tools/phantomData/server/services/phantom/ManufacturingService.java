package org.artorg.tools.phantomData.server.services.phantom;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.phantom.Manufacturing;
import org.artorg.tools.phantomData.server.repositories.phantom.ManufacturingRepository;
import org.artorg.tools.phantomData.server.service.IServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ManufacturingService implements IServiceDefault<Manufacturing> {

	@Autowired
	private ManufacturingRepository repository;
	
	@Override
	public CrudRepository<Manufacturing, UUID> getRepository() {
		return repository;
	}

}
