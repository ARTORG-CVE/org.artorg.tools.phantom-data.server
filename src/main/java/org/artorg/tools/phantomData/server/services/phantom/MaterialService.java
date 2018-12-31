package org.artorg.tools.phantomData.server.services.phantom;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.phantom.Material;
import org.artorg.tools.phantomData.server.repositories.phantom.MaterialRepository;
import org.artorg.tools.phantomData.server.service.IServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class MaterialService implements IServiceDefault<Material> {

	@Autowired
	private MaterialRepository repository;
	
	@Override
	public CrudRepository<Material, UUID> getRepository() {
		return repository;
	}

}
