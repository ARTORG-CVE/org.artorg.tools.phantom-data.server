package org.artorg.tools.phantomData.server.service.base.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.base.property.PropertyField;
import org.artorg.tools.phantomData.server.repository.property.PropertyFieldRepository;
import org.artorg.tools.phantomData.server.serviceSpec.base.property.IpropertyFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyFieldService implements IpropertyFieldService<PropertyField> {
	
	@Autowired
	private PropertyFieldRepository repository;

	@Override
	public CrudRepository<PropertyField, UUID> getRepository() {
		return repository;
	}
	
	@Override
	public PropertyField getByName(String name) {
		List<PropertyField> list = repository.findByName(name);
		return list.get(0);
	}
}
