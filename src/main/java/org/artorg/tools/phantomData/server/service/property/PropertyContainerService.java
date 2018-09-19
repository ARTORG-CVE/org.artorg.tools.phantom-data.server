package org.artorg.tools.phantomData.server.service.property;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.property.PropertyContainer;
import org.artorg.tools.phantomData.server.repository.property.PropertyContainerRepository;
import org.artorg.tools.phantomData.server.service.iService.property.IpropertyContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyContainerService implements IpropertyContainerService<PropertyContainer> {

	@Autowired
	private PropertyContainerRepository repository;
	
	@Override
	public CrudRepository<PropertyContainer, UUID> getRepository() {
		return repository;
	}

}
