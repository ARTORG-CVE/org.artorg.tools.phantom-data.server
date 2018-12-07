package org.artorg.tools.phantomData.server.service.phantom;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.phantom.Manufacturing;
import org.artorg.tools.phantomData.server.repository.phantom.ManufacturingRepository;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;
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
