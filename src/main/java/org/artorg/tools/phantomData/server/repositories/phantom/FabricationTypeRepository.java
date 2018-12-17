package org.artorg.tools.phantomData.server.repositories.phantom;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.models.phantom.FabricationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "FABRICATION_TYPES")
public interface FabricationTypeRepository extends CrudRepository<FabricationType, UUID> {
	
	List<FabricationType> findByShortcut(String shortcut);

}