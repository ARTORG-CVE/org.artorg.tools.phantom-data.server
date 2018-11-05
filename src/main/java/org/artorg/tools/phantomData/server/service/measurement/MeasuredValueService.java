package org.artorg.tools.phantomData.server.service.measurement;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.measurement.MeasuredValue;
import org.artorg.tools.phantomData.server.repository.measurement.MeasuredValueRepository;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class MeasuredValueService implements IService<MeasuredValue> {

	@Autowired
	private MeasuredValueRepository repository;
	
	@Override
	public CrudRepository<MeasuredValue, UUID> getRepository() {
		return repository;
	}

}
