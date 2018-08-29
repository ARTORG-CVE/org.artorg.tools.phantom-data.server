package org.artorg.tools.phantomData.server.service.property;

import org.artorg.tools.phantomData.server.model.property.PropertyContainer;
import org.artorg.tools.phantomData.server.repository.property.PropertyContainerRepository;
import org.artorg.tools.phantomData.server.service.iService.property.IpropertyContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyContainerService implements IpropertyContainerService<PropertyContainer, Integer> {

	@Autowired
	private PropertyContainerRepository propertyContainerRepository;
	
	@Override
	public CrudRepository<PropertyContainer, Integer> getRepository() {
		return propertyContainerRepository;
	}

}
