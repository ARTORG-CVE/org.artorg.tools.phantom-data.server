package org.artorg.tools.phantomData.server.services.base;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.base.FileTag;
import org.artorg.tools.phantomData.server.repositories.base.FileTagRepository;
import org.artorg.tools.phantomData.server.service.IServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class FileTagService implements IServiceDefault<FileTag> {

	@Autowired
	private FileTagRepository repository;
	
	@Override
	public CrudRepository<FileTag, UUID> getRepository() {
		return repository;
	}

}
