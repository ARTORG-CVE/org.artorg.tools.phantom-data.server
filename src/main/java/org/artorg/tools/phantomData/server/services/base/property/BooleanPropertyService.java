package org.artorg.tools.phantomData.server.services.base.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.models.base.property.BooleanProperty;
import org.artorg.tools.phantomData.server.models.base.property.PropertyField;
import org.artorg.tools.phantomData.server.repositories.base.property.BooleanPropertyRepository;
import org.artorg.tools.phantomData.server.serviceSpec.base.property.IbooleanPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class BooleanPropertyService implements IbooleanPropertyService<BooleanProperty> {
	
	@Autowired
	private BooleanPropertyRepository repository;
	
	@Override
	public CrudRepository<BooleanProperty, UUID> getRepository() {
		return repository;
	}

	@Override
	public BooleanProperty getByPropertyField(PropertyField propertyField) {
		List<BooleanProperty> list = repository.findByPropertyField(propertyField);
		return list.get(0);
	}

}
