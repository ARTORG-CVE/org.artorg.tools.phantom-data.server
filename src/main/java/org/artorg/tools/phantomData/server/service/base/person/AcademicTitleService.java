package org.artorg.tools.phantomData.server.service.base.person;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.base.person.AcademicTitle;
import org.artorg.tools.phantomData.server.repository.base.person.AcademicTitleRepository;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class AcademicTitleService implements IService<AcademicTitle> {

	@Autowired
	private AcademicTitleRepository repository;
	
	@Override
	public CrudRepository<AcademicTitle, UUID> getRepository() {
		return repository;
	}

}
