package org.artorg.tools.phantomData.server.repository;

import java.util.List;

import org.artorg.tools.phantomData.server.model.LiteratureBase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "LITERATURE_BASES")
public interface LiteratureBaseRepository extends CrudRepository<LiteratureBase, Integer> {
	
	List<LiteratureBase> findByShortcut(String shortcut);
}
