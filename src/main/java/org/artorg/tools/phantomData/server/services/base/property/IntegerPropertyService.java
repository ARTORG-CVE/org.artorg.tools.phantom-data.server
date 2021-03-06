package org.artorg.tools.phantomData.server.services.base.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.models.base.property.IntegerProperty;
import org.artorg.tools.phantomData.server.models.base.property.PropertyField;
import org.artorg.tools.phantomData.server.repositories.base.property.IntegerPropertyRepository;
import org.artorg.tools.phantomData.server.serviceSpec.base.property.IintegerPropertyService;
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