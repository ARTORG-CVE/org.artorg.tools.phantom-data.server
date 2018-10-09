package org.artorg.tools.phantomData.server.service;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.Person;
import org.artorg.tools.phantomData.server.repository.PersonRepository;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IService<Person> {

	@Autowired
	private PersonRepository repository;
	
	@Override
	public CrudRepository<Person, UUID> getRepository() {
		return repository;
	}

}
