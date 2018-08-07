package org.artorg.tools.phantomData.server.repository;

import java.util.List;

import org.artorg.tools.phantomData.server.model.AnnulusDiameter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "ANNULUS_DIAMETERS")
public interface AnnulusDiameterRepository extends CrudRepository<AnnulusDiameter, Integer> {
	
	List<AnnulusDiameter> findByShortcut(Integer shortcut);
	
}