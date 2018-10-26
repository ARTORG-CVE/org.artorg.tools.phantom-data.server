package org.artorg.tools.phantomData.server.service;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.phantom.LiteratureBase;
import org.artorg.tools.phantomData.server.repository.LiteratureBaseRepository;
import org.artorg.tools.phantomData.server.service.iService.IliteratureBaseService;
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
