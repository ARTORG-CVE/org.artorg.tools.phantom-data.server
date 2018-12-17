package org.artorg.tools.phantomData.server.services.phantom;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.phantom.Phantom;
import org.artorg.tools.phantomData.server.repositories.phantom.PhantomRepository;
import org.artorg.tools.phantomData.server.service.IServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PhantomService implements IServiceDefault<Phantom> {

	@Autowired
	private PhantomRepository repository;
	
	@Override
	public CrudRepository<Phantom, UUID> getRepository() {
		return repository;
	}

}
