package org.artorg.tools.phantomData.server.service.property;

import java.util.List;

import org.artorg.tools.phantomData.server.model.property.DoubleProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.repository.property.DoublePropertyRepository;
import org.artorg.tools.phantomData.server.service.iService.property.IdoublePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class DoublePropertyService implements IdoublePropertyService<DoubleProperty, Integer> {
	
	@Autowired
	private DoublePropertyRepository repository;
	
	@Override
	public CrudRepository<DoubleProperty, Integer> getRepository() {
		return repository;
	}

	@Override
	public DoubleProperty getByPropertyField(PropertyField propertyField) {
		List<DoubleProperty> list = repository.findByPropertyField(propertyField);
		return list.get(0);
	}

}
