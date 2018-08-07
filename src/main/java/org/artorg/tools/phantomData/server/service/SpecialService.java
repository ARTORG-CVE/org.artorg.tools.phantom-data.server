package org.artorg.tools.phantomData.server.service;

import java.util.List;

import org.artorg.tools.phantomData.server.model.Special;
import org.artorg.tools.phantomData.server.repository.SpecialRepository;
import org.artorg.tools.phantomData.server.service.iService.IspecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class SpecialService implements IspecialService<Special, Integer> {

	@Autowired
	private SpecialRepository specialRepository;
	
	@Override
	public CrudRepository<Special, Integer> getRepository() {
		return specialRepository;
	}

	@Override
	public Special getByShortcut(String shortcut) {
		List<Special> list = specialRepository.findByShortcut(shortcut);
		return list.get(0);
	}

}
