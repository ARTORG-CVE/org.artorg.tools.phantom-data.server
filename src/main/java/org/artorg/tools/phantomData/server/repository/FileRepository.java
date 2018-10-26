package org.artorg.tools.phantomData.server.repository;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.DbFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "FILES")
public interface FileRepository extends CrudRepository<DbFile, UUID> {
	
	List<DbFile> findByName(String name);
	
}
