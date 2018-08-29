package org.artorg.tools.phantomData.server.service;

import org.artorg.tools.phantomData.server.model.Phantom;
import org.artorg.tools.phantomData.server.repository.PhantomRepository;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PhantomService implements IService<Phantom, Integer> {

	@Autowired
	private PhantomRepository repository;
	
	@Override
	public CrudRepository<Phantom, Integer> getRepository() {
		return repository;
	}

}
