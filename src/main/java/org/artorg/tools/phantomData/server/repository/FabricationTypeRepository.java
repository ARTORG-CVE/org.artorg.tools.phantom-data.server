package org.artorg.tools.phantomData.server.repository;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.phantom.FabricationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "FABRICATION_TYPES")
public interface FabricationTypeRepository extends CrudRepository<FabricationType, UUID> {
	
	List<FabricationType> findByShortcut(String shortcut);

}