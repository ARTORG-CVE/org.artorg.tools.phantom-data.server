package org.artorg.tools.phantomData.server.repository.phantom;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.phantom.LiteratureBase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "LITERATURE_BASES")
public interface LiteratureBaseRepository extends CrudRepository<LiteratureBase, UUID> {
	
	List<LiteratureBase> findByShortcut(String shortcut);
}
