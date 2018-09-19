package org.artorg.tools.phantomData.server.service.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.property.IntegerProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.repository.property.IntegerPropertyRepository;
import org.artorg.tools.phantomData.server.service.iService.property.IintegerPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class IntegerPropertyService implements IintegerPropertyService<IntegerProperty> {
	
	@Autowired
	private IntegerPropertyRepository repository;
	
	@Override
	public CrudRepository<IntegerProperty, UUID> getRepository() {
		return repository;
	}

	@Override
	public IntegerProperty getByPropertyField(PropertyField propertyField) {
		List<IntegerProperty> list = repository.findByPropertyField(propertyField);
		return list.get(0);
	}

}