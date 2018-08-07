package org.artorg.tools.phantomData.server.service;

import java.util.List;

import org.artorg.tools.phantomData.server.model.LiteratureBase;
import org.artorg.tools.phantomData.server.repository.LiteratureBaseRepository;
import org.artorg.tools.phantomData.server.service.iService.IliteratureBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class LiteratureBaseService implements IliteratureBaseService<LiteratureBase, Integer> {
	@Autowired
	private LiteratureBaseRepository literatureBaseRepository;

	@Override
	public CrudRepository<LiteratureBase, Integer> getRepository() {
		return literatureBaseRepository;
	}

	@Override
	public LiteratureBase getByShortcut(String shortcut) {
		List<LiteratureBase> list = literatureBaseRepository.findByShortcut(shortcut);
		return list.get(0);
	}
	
	
	
}
