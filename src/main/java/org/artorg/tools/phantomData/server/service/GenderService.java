package org.artorg.tools.phantomData.server.service;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.person.Gender;
import org.artorg.tools.phantomData.server.repository.GenderRepository;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class GenderService implements IService<Gender> {

	@Autowired
	private GenderRepository repository;
	
	@Override
	public CrudRepository<Gender, UUID> getRepository() {
		return repository;
	}

}