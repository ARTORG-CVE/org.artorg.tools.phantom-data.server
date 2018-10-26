package org.artorg.tools.phantomData.server.repository;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.person.Gender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "GENDERS")
public interface GenderRepository extends CrudRepository<Gender, UUID> {

}
