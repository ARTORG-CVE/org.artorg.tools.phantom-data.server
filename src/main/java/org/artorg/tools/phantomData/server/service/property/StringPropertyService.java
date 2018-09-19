package org.artorg.tools.phantomData.server.service.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.model.property.StringProperty;
import org.artorg.tools.phantomData.server.repository.property.StringPropertyRepository;
import org.artorg.tools.phantomData.server.service.iService.property.IstringPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class StringPropertyService implements IstringPropertyService<StringProperty> {
	
	@Autowired
	private StringPropertyRepository repository;
	
	@Override
	public CrudRepository<StringProperty, UUID> getRepository() {
		return repository;
	}

	@Override
	public StringProperty getByPropertyField(PropertyField propertyField) {
		List<StringProperty> list = repository.findByPropertyField(propertyField);
		return list.get(0);
	}

}
