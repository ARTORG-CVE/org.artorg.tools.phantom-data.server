package org.artorg.tools.phantomData.server.repository;

import java.util.List;

import org.artorg.tools.phantomData.server.model.FabricationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "FABRICATION_TYPES")
public interface FabricationTypeRepository extends CrudRepository<FabricationType, Integer> {
	
	List<FabricationType> findByShortcut(String shortcut);

}