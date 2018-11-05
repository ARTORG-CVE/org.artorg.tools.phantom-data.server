package org.artorg.tools.phantomData.server.service.phantom;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.phantom.AnnulusDiameter;
import org.artorg.tools.phantomData.server.repository.phantom.AnnulusDiameterRepository;
import org.artorg.tools.phantomData.server.serviceSpec.phantom.IannulusDiameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class AnnulusDiameterService implements IannulusDiameterService<AnnulusDiameter> {
	
	@Autowired
	private AnnulusDiameterRepository repository;

	@Override
	public CrudRepository<AnnulusDiameter, UUID> getRepository() {
		return repository;
	}
	
	@Override
	public AnnulusDiameter getByShortcut(Integer shortcut) {
		List<AnnulusDiameter> list = repository.findByShortcut(shortcut);
		if (list.size()==1) return list.get(0); 
		return list.stream().filter(ad -> ad.getValue().equals(Double.valueOf(shortcut)))
				.findFirst().get();
	}

}
