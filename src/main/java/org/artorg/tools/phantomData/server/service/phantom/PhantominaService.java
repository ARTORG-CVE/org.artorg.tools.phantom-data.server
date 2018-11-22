package org.artorg.tools.phantomData.server.service.phantom;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.phantom.Phantomina;
import org.artorg.tools.phantomData.server.repository.phantom.PhantominaRepository;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PhantominaService implements IServiceDefault<Phantomina> {

	@Autowired
	private PhantominaRepository repository;
	
	@Override
	public CrudRepository<Phantomina, UUID> getRepository() {
		return repository;
	}

}
