package org.artorg.tools.phantomData.server.service.measurement;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.measurement.Unit;
import org.artorg.tools.phantomData.server.repository.measurement.UnitRepository;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UnitService implements IServiceDefault<Unit> {

	@Autowired
	private UnitRepository repository;
	
	@Override
	public CrudRepository<Unit, UUID> getRepository() {
		return repository;
	}

}
