package org.artorg.tools.phantomData.server.service.phantom;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.phantom.FabricationType;
import org.artorg.tools.phantomData.server.repository.phantom.FabricationTypeRepository;
import org.artorg.tools.phantomData.server.serviceSpec.phantom.IfabricationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class FabricationTypeService implements IfabricationTypeService<FabricationType> {

	@Autowired
	private FabricationTypeRepository repository;
	
	@Override
	public CrudRepository<FabricationType, UUID> getRepository() {
		return repository;
	}

	@Override
	public FabricationType getByShortcut(String shortcut) {
		List<FabricationType> list = repository.findByShortcut(shortcut);
		return list.get(0);
	}

}
