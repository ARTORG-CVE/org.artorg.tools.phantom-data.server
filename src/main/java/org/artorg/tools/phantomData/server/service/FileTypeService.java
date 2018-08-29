package org.artorg.tools.phantomData.server.service;

import org.artorg.tools.phantomData.server.model.FileType;
import org.artorg.tools.phantomData.server.repository.FileTypeRepository;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class FileTypeService implements IService<FileType, Integer> {

	@Autowired
	private FileTypeRepository repository;
	
	@Override
	public CrudRepository<FileType, Integer> getRepository() {
		return repository;
	}

}
