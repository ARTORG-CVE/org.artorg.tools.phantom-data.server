package org.artorg.tools.phantomData.server.services.base.person;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.base.person.AcademicTitle;
import org.artorg.tools.phantomData.server.repositories.base.person.AcademicTitleRepository;
import org.artorg.tools.phantomData.server.service.IServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class AcademicTitleService implements IServiceDefault<AcademicTitle> {

	@Autowired
	private AcademicTitleRepository repository;
	
	@Override
	public CrudRepository<AcademicTitle, UUID> getRepository() {
		return repository;
	}

}
