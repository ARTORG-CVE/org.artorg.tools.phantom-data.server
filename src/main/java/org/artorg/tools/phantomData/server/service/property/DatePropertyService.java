package org.artorg.tools.phantomData.server.service.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.property.DateProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.repository.property.DatePropertyRepository;
import org.artorg.tools.phantomData.server.service.iService.property.IdatePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class DatePropertyService implements IdatePropertyService<DateProperty> {

	@Autowired
	private DatePropertyRepository repository;
	
	@Override
	public CrudRepository<DateProperty, UUID> getRepository() {
		return repository;
	}
	
	@Override
	public DateProperty getByPropertyField(PropertyField propertyField) {
		List<DateProperty> list = repository.findByPropertyField(propertyField);
		return list.get(0);
	}

}
