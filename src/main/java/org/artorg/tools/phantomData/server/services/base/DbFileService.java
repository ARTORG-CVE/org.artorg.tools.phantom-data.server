package org.artorg.tools.phantomData.server.services.base;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.models.base.DbFile;
import org.artorg.tools.phantomData.server.repositories.base.DbFileRepository;
import org.artorg.tools.phantomData.server.serviceSpec.IfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class DbFileService implements IfileService<DbFile> {

	@Autowired
	private DbFileRepository repository;
	
	@Override
	public CrudRepository<DbFile, UUID> getRepository() {
		return repository;
	}

	@Override
	public DbFile getByName(String name) {
		List<DbFile> list = repository.findByName(name);
		if (list.size()==1) return list.get(0); 
		return list.stream().filter(ad -> ad.getName().equals(Double.valueOf(name)))
				.findFirst().get();
	}

}
