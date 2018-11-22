package org.artorg.tools.phantomData.server.service.base.person;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.base.person.Person;
import org.artorg.tools.phantomData.server.repository.base.person.PersonRepository;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IServiceDefault<Person> {

	@Autowired
	private PersonRepository repository;
	
	@Override
	public CrudRepository<Person, UUID> getRepository() {
		return repository;
	}

}
