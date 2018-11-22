package org.artorg.tools.phantomData.server.service.measurement;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.measurement.PhysicalQuantity;
import org.artorg.tools.phantomData.server.repository.measurement.PhysicalQuantityRepository;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PhysicalQuantityService implements IServiceDefault<PhysicalQuantity> {

	@Autowired
	private PhysicalQuantityRepository repository;
	
	@Override
	public CrudRepository<PhysicalQuantity, UUID> getRepository() {
		return repository;
	}

}