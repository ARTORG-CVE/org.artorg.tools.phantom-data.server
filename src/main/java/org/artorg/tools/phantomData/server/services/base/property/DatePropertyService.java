package org.artorg.tools.phantomData.server.services.base.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.models.base.property.DateProperty;
import org.artorg.tools.phantomData.server.models.base.property.PropertyField;
import org.artorg.tools.phantomData.server.repositories.base.property.DatePropertyRepository;
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
