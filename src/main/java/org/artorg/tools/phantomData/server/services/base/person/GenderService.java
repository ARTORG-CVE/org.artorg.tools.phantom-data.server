package org.artorg.tools.phantomData.server.services.base.person;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.base.person.Gender;
import org.artorg.tools.phantomData.server.repositories.base.person.GenderRepository;
import org.artorg.tools.phantomData.server.service.IServiceDefault;
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