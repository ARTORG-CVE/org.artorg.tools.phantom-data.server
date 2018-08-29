package org.artorg.tools.phantomData.server.service.property;

import java.util.List;

import org.artorg.tools.phantomData.server.model.property.BooleanProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.repository.property.BooleanPropertyRepository;
import org.artorg.tools.phantomData.server.service.iService.property.IbooleanPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class BooleanPropertyService implements IbooleanPropertyService<BooleanProperty, Integer> {
	
	@Autowired
	private BooleanPropertyRepository repository;
	
	@Override
	public CrudRepository<BooleanProperty, Integer> getRepository() {
		return repository;
	}

	@Override
	public BooleanProperty getByPropertyField(PropertyField propertyField) {
		List<BooleanProperty> list = repository.findByPropertyField(propertyField);
		return list.get(0);
	}

}
