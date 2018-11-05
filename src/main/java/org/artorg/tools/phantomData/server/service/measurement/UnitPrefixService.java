package org.artorg.tools.phantomData.server.service.measurement;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.measurement.UnitPrefix;
import org.artorg.tools.phantomData.server.repository.measurement.UnitPrefixRepository;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UnitPrefixService implements IService<UnitPrefix> {

	@Autowired
	private UnitPrefixRepository repository;
	
	@Override
	public CrudRepository<UnitPrefix, UUID> getRepository() {
		return repository;
	}

}
