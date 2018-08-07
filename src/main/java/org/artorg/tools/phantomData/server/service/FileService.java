package org.artorg.tools.phantomData.server.service;

import java.util.List;

import org.artorg.tools.phantomData.server.model.PhantomFile;
import org.artorg.tools.phantomData.server.repository.FileRepository;
import org.artorg.tools.phantomData.server.service.iService.IfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class FileService implements IfileService<PhantomFile, Integer> {

	@Autowired
	private FileRepository fileRepository;
	
	@Override
	public CrudRepository<PhantomFile, Integer> getRepository() {
		return fileRepository;
	}

	@Override
	public PhantomFile getByName(String name) {
		List<PhantomFile> list = fileRepository.findByName(name);
		if (list.size()==1) return list.get(0); 
		return list.stream().filter(ad -> ad.getName().equals(Double.valueOf(name)))
				.findFirst().get();
	}

}
