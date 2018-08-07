package org.artorg.tools.phantomData.server.service;

import java.util.List;

import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.repository.property.PropertyFieldRepository;
import org.artorg.tools.phantomData.server.service.iService.property.IpropertyFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class PropertyFieldService implements IpropertyFieldService<PropertyField, Integer> {

	@Autowired
	private PropertyFieldRepository propertyFieldRepository;
	
	@Override
	public CrudRepository<PropertyField, Integer> getRepository() {
		return propertyFieldRepository;
	}

	@Override
	public PropertyField getByName(String name) {
		List<PropertyField> list = propertyFieldRepository.findByName(name);
		return list.get(0);
	}

}
