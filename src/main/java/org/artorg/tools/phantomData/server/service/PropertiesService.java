package org.artorg.tools.phantomData.server.service;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.Properties;
import org.artorg.tools.phantomData.server.repository.PropertiesRepository;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertiesService implements IService<Properties> {

	@Autowired
	private PropertiesRepository repository;
	
	@Override
	public CrudRepository<Properties, UUID> getRepository() {
		return repository;
	}

}