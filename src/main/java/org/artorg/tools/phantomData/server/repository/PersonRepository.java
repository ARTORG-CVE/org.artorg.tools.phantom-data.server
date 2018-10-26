package org.artorg.tools.phantomData.server.repository;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.person.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "PERSONS")
public interface PersonRepository extends CrudRepository<Person, UUID> {

}
