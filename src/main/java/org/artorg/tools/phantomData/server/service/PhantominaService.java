package org.artorg.tools.phantomData.server.service;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.Phantomina;
import org.artorg.tools.phantomData.server.repository.PhantominaRepository;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PhantominaService implements IService<Phantomina> {

	@Autowired
	private PhantominaRepository repository;
	
	@Override
	public CrudRepository<Phantomina, UUID> getRepository() {
		return repository;
	}

}
