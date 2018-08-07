package org.artorg.tools.phantomData.server.repository;

import java.util.List;

import org.artorg.tools.phantomData.server.model.PhantomFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "FILES")
public interface FileRepository extends CrudRepository<PhantomFile, Integer> {
	
	List<PhantomFile> findByName(String name);
	
}
