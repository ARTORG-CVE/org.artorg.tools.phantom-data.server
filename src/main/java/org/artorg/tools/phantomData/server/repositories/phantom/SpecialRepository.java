package org.artorg.tools.phantomData.server.repositories.phantom;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.models.phantom.Special;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "SPECIALS")
public interface SpecialRepository extends CrudRepository<Special, UUID> {
	
	List<Special> findByShortcut(String shortcut);
	
}