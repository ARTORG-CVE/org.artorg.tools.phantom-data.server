package org.artorg.tools.phantomData.server.service.base.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.base.property.DateProperty;
import org.artorg.tools.phantomData.server.model.base.property.PropertyField;
import org.artorg.tools.phantomData.server.repository.base.property.DatePropertyRepository;
import org.artorg.tools.phantomData.server.serviceSpec.base.property.IdatePropertyService;
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
