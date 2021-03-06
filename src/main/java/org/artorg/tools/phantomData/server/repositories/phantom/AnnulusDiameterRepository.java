package org.artorg.tools.phantomData.server.repositories.phantom;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.models.phantom.AnnulusDiameter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "ANNULUS_DIAMETERS")
public interface AnnulusDiameterRepository extends CrudRepository<AnnulusDiameter, UUID> {
	
	List<AnnulusDiameter> findByShortcut(Integer shortcut);
	
}