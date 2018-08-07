package org.artorg.tools.phantomData.server.repository;

import java.util.List;

import org.artorg.tools.phantomData.server.model.Special;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "SPECIALS")
public interface SpecialRepository extends CrudRepository<Special, Integer> {
	
	List<Special> findByShortcut(String shortcut);
	
}