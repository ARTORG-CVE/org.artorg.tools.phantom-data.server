package org.artorg.tools.phantomData.server.service.base.person;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.base.person.Gender;
import org.artorg.tools.phantomData.server.repository.base.person.GenderRepository;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class GenderService implements IServiceDefault<Gender> {

	@Autowired
	private GenderRepository repository;
	
	@Override
	public CrudRepository<Gender, UUID> getRepository() {
		return repository;
	}

}