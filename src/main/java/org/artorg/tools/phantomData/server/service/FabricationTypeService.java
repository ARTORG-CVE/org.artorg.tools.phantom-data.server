package org.artorg.tools.phantomData.server.service;

import java.util.List;

import org.artorg.tools.phantomData.server.model.FabricationType;
import org.artorg.tools.phantomData.server.repository.FabricationTypeRepository;
import org.artorg.tools.phantomData.server.service.iService.IfabricationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class FabricationTypeService implements IfabricationTypeService<FabricationType, Integer> {

	@Autowired
	private FabricationTypeRepository fabricationTypeRepository;
	
	@Override
	public CrudRepository<FabricationType, Integer> getRepository() {
		return fabricationTypeRepository;
	}

	@Override
	public FabricationType getByShortcut(String shortcut) {
		List<FabricationType> list = fabricationTypeRepository.findByShortcut(shortcut);
		return list.get(0);
	}

}
