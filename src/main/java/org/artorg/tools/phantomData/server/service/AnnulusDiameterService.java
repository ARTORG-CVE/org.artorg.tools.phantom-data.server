package org.artorg.tools.phantomData.server.service;

import java.util.List;

import org.artorg.tools.phantomData.server.model.AnnulusDiameter;
import org.artorg.tools.phantomData.server.repository.AnnulusDiameterRepository;
import org.artorg.tools.phantomData.server.service.iService.IannulusDiameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class AnnulusDiameterService implements IannulusDiameterService<AnnulusDiameter, Integer> {
	
	@Autowired
	private AnnulusDiameterRepository annulusDiameterRepository;

	@Override
	public CrudRepository<AnnulusDiameter, Integer> getRepository() {
		return annulusDiameterRepository;
	}
	
	@Override
	public AnnulusDiameter getByShortcut(Integer shortcut) {
		List<AnnulusDiameter> list = annulusDiameterRepository.findByShortcut(shortcut);
		if (list.size()==1) return list.get(0); 
		return list.stream().filter(ad -> ad.getValue().equals(Double.valueOf(shortcut)))
				.findFirst().get();
	}

}
