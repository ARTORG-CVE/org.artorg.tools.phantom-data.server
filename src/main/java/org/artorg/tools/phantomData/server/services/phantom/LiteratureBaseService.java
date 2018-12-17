package org.artorg.tools.phantomData.server.services.phantom;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.models.phantom.LiteratureBase;
import org.artorg.tools.phantomData.server.repositories.phantom.LiteratureBaseRepository;
import org.artorg.tools.phantomData.server.serviceSpec.phantom.IliteratureBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class LiteratureBaseService implements IliteratureBaseService<LiteratureBase> {
	@Autowired
	private LiteratureBaseRepository repository;

	@Override
	public CrudRepository<LiteratureBase, UUID> getRepository() {
		return repository;
	}

	@Override
	public LiteratureBase getByShortcut(String shortcut) {
		List<LiteratureBase> list = repository.findByShortcut(shortcut);
		return list.get(0);
	}
	
	
	
}
